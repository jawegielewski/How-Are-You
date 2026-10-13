package pl.jawegiel.howareyou.data.datasource

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.MutableData
import com.google.firebase.database.Transaction
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import pl.jawegiel.howareyou.data.model.Post
import pl.jawegiel.howareyou.data.repository.PostRepositoryImpl.Companion.TAG
import javax.inject.Inject

class FirebasePostsRemoteDataSourceImpl @Inject constructor(
    private val firebaseDb: FirebaseDatabase
) : FirebasePostsRemoteDataSource {

    override suspend fun addPost(postText: String, userId: String) {
        val map = HashMap<String?, Any?>().apply {
            put("text", postText)
            put("user_id", userId)
            put("publish_date", LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli())
            put("likes", 0)
            put("comments", mutableListOf<String>())
        }

        firebaseDb.getReference("posts").runTransaction(object : Transaction.Handler {
            override fun doTransaction(mutableData: MutableData): Transaction.Result { // 1. Get the current number of posts
                val currentCount = mutableData.childrenCount

                // 2. Determine the next sequential index (1, 2, 3...)
                val nextId = (currentCount + 1).toString()

                // 3. Set the map value inside the new sequential child path
                mutableData.child(nextId).value = map

                return Transaction.success(mutableData)
            }

            override fun onComplete(
                error: DatabaseError?, committed: Boolean, snapshot: DataSnapshot?
            ) {
                if (error != null) { // Handle transaction failure (e.g., logging or network errors)
                    Log.e(TAG, "Transaction failed: ${error.message}")
                }
            }
        })
    }

    override fun observePosts(): Flow<List<Post>> = callbackFlow {
        val dbRef = firebaseDb.getReference("posts")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemsList = snapshot.children.mapNotNull { child ->
                    val dto = child.getValue(Post::class.java)
                    dto?.let { Post(id = child.key, text = it.text, interaction = it.interaction) }
                }
                trySend(itemsList)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        dbRef.addValueEventListener(listener)

        awaitClose { dbRef.removeEventListener(listener) }
    }

    override fun observeMyPosts(userId: String): Flow<List<Post>> =
        callbackFlow {

            val postsQuery =
                firebaseDb.getReference("posts").orderByChild("user_id").equalTo(userId)

            val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val postsList = snapshot.children.mapNotNull { child ->
                        val dto = child.getValue(Post::class.java)
                        dto?.let {
                            Post(
                                id = child.key,
                                text = it.text,
                                userId = it.userId,
                                interaction = it.interaction
                            )
                        }
                    }
                    trySend(postsList)
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            }

            postsQuery.addValueEventListener(listener)

            awaitClose {
                postsQuery.removeEventListener(listener)
            }
        }
}