package pl.jawegiel.howareyou.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.jawegiel.howareyou.data.repository.PostRepositoryImpl
import pl.jawegiel.howareyou.data.repository.UserRepositoryImpl
import pl.jawegiel.howareyou.domain.repository.PostRepository
import pl.jawegiel.howareyou.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
