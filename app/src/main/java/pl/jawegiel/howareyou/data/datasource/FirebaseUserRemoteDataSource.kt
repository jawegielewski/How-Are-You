package pl.jawegiel.howareyou.data.datasource

import com.google.firebase.database.DataSnapshot

interface FirebaseUserRemoteDataSource {
    suspend fun getSnapshot(): DataSnapshot
    fun addUserId(parentKey: String, userId: String)
}