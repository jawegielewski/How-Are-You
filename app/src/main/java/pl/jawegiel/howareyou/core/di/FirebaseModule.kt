package pl.jawegiel.howareyou.core.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.jawegiel.howareyou.data.datasource.FirebasePostsRemoteDataSource
import pl.jawegiel.howareyou.data.datasource.FirebasePostsRemoteDataSourceImpl
import pl.jawegiel.howareyou.data.datasource.FirebaseUserRemoteDataSource
import pl.jawegiel.howareyou.data.datasource.FirebaseUserRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseModule {

    @Binds
    @Singleton
    abstract fun bindFirebaseUserRemoteDataSource(
        impl: FirebaseUserRemoteDataSourceImpl
    ): FirebaseUserRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindFirebasePostsRemoteDataSource(
        impl: FirebasePostsRemoteDataSourceImpl
    ): FirebasePostsRemoteDataSource

    companion object {
        @Provides
        @Singleton
        fun provideFirebaseDatabase(): FirebaseDatabase {
            return FirebaseDatabase.getInstance()
        }
    }
}
