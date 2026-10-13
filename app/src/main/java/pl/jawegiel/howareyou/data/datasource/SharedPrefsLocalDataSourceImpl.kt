package pl.jawegiel.howareyou.data.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class SharedPrefsLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : SharedPrefsLocalDataSource {

    companion object {
        private const val KEY_USER_ID = "key_user_id"
    }

    override fun addUserId(userId: String) {
        sharedPreferences.edit { putString(KEY_USER_ID, userId) }
    }

    override fun getUserId(): String? {
        return sharedPreferences.getString(KEY_USER_ID, null)
    }
}