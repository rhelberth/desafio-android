package com.picpay.desafio.android.data.di

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.android.data.local.DbConstants
import com.picpay.desafio.android.data.local.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): UserDataBase = Room.databaseBuilder(
        context,
        UserDataBase::class.java,
        DbConstants.APP_DATABASE_NAME
    ).build()

    @Provides
    fun provideUserDao(appDatabase: UserDataBase) = appDatabase.userDao()

}