package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.repository.FakeUserRepository
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.repository.di.RepositoryModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
interface FakeUserRepositoryModule {
    @Binds
    @Singleton
    fun provideFakeUserRepository(fakeUserRepository: FakeUserRepository): UserRepository
}