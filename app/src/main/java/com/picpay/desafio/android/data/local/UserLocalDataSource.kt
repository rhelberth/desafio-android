package com.picpay.desafio.android.data.local

import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    suspend fun getAllUser(): List<User>
    suspend fun saveAllUser(userList: List<UserEntity>)
}