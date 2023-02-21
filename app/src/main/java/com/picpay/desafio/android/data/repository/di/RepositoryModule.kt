package com.picpay.desafio.android.data.repository.di

import com.picpay.desafio.android.data.local.RoomUserDataSource
import com.picpay.desafio.android.data.local.UserLocalDataSource
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindUserRepository(productRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindRoomUserDataSource(roomUserDataSource: RoomUserDataSource): UserLocalDataSource

}