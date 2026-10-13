package pl.jawegiel.howareyou.data.datasource

interface SharedPrefsLocalDataSource {
    fun addUserId(userId: String)
    fun getUserId(): String?
}