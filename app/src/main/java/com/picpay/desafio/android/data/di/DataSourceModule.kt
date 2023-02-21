package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.data.remote.RetrofitUserRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Singleton
    @Binds
    fun bindUserDataSource(retrofitUserDataSource: RetrofitUserRemoteDataSource): UserRemoteDataSource

}