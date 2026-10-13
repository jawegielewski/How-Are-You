package pl.jawegiel.howareyou.data.datasource

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseUserRemoteDataSourceImpl @Inject constructor(
    private val firebaseDb: FirebaseDatabase
): FirebaseUserRemoteDataSource {

    override suspend fun getSnapshot(): DataSnapshot  {
        return firebaseDb.getReference("user_ids").get().await()
    }

    override fun addUserId(parentKey: String, userId: String) {
        firebaseDb.getReference("user_ids").child(parentKey).setValue(mapOf("user_id" to userId))
    }
}