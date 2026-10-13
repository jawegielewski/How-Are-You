package pl.jawegiel.howareyou.domain.repository

import kotlinx.coroutines.flow.Flow
import pl.jawegiel.howareyou.data.model.Post

interface PostRepository {
    suspend fun addPost(postText: String)
    fun getPosts(): Flow<List<Post>>
    fun getMyPosts(): Flow<List<Post>>
}