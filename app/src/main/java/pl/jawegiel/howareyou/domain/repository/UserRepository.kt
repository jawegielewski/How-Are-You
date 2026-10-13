package pl.jawegiel.howareyou.domain.repository

interface UserRepository {
    suspend fun getOrGenerateUserId(): String
}
