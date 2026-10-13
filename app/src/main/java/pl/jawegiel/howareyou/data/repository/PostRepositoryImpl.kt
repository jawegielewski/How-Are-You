package pl.jawegiel.howareyou.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import pl.jawegiel.howareyou.data.datasource.FirebasePostsRemoteDataSource
import pl.jawegiel.howareyou.data.model.Post
import pl.jawegiel.howareyou.domain.repository.PostRepository
import pl.jawegiel.howareyou.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(
    private val firebasePostsRemoteDataSource: FirebasePostsRemoteDataSource,
    private val userRepository: UserRepository
) : PostRepository {

    companion object {
        val TAG = PostRepositoryImpl::class.simpleName
    }

    override suspend fun addPost(postText: String) {
        val userId = userRepository.getOrGenerateUserId()

        firebasePostsRemoteDataSource.addPost(postText, userId)
    }


    override fun getPosts(): Flow<List<Post>> = flow {
        val postsFlow = firebasePostsRemoteDataSource.observePosts()
        emitAll(postsFlow)
    }


    override fun getMyPosts(): Flow<List<Post>> = flow { // 1. Fetch the user ID dynamically
        val userId = userRepository.getOrGenerateUserId()

        val myPostsFlow = firebasePostsRemoteDataSource.observeMyPosts(userId)
        emitAll(myPostsFlow)
    }
}