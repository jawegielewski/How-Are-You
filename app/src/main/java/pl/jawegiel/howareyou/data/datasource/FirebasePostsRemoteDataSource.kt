package pl.jawegiel.howareyou.data.datasource

import kotlinx.coroutines.flow.Flow
import pl.jawegiel.howareyou.data.model.Post

interface FirebasePostsRemoteDataSource {
    suspend fun addPost(postText: String, userId: String)
    fun observePosts(): Flow<List<Post>>
    fun observeMyPosts(userId: String): Flow<List<Post>>
}