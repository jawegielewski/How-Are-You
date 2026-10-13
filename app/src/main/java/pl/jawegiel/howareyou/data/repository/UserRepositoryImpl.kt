package pl.jawegiel.howareyou.data.repository

import pl.jawegiel.howareyou.data.datasource.FirebaseUserRemoteDataSource
import pl.jawegiel.howareyou.data.datasource.SharedPrefsLocalDataSource
import pl.jawegiel.howareyou.domain.repository.UserRepository
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val localDataSource: SharedPrefsLocalDataSource,
    private val remoteDataSource: FirebaseUserRemoteDataSource,
) : UserRepository {

    override suspend fun getOrGenerateUserId(): String {
        val userId = localDataSource.getUserId()
        if (!userId.isNullOrEmpty()) {
            return userId
        }

        val snapshot = remoteDataSource.getSnapshot()
        val nextParentKey = (snapshot.childrenCount + 1).toString()

        val existingUserIds = snapshot.children.mapNotNull { child ->
            child.child("user_id").getValue(String::class.java)
        }.toSet()

        var randomUserId: String
        do {
            randomUserId = UUID.randomUUID().toString()
        } while (existingUserIds.contains(randomUserId))

        addUserId(nextParentKey, randomUserId)
        localDataSource.addUserId(randomUserId)
        return randomUserId
    }

    fun addUserId(parentKey: String, userId: String) {
        remoteDataSource.addUserId(parentKey, userId)
    }
}
