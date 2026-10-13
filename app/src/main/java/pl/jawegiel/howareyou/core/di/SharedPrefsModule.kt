package pl.jawegiel.howareyou.core.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.jawegiel.howareyou.data.datasource.SharedPrefsLocalDataSource
import pl.jawegiel.howareyou.data.datasource.SharedPrefsLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SharedPrefsModule {

    @Binds
    @Singleton
    abstract fun bindSharedPrefsDataSource(
        impl: SharedPrefsLocalDataSourceImpl
    ): SharedPrefsLocalDataSource

    companion object {
        @Provides
        @Singleton
        fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
            return context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        }
    }
}