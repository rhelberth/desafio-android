package com.picpay.desafio.android.data.local

import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.data.local.entity.toModel
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RoomUserDataSource @Inject constructor(
    private val userDao: UserDao
) : UserLocalDataSource {
    override suspend fun getAllUser(): List<User> {

        return withContext(Dispatchers.IO){
            userDao.getAll().map {
                it.toModel()
            }
        }

    }

    override suspend fun saveAllUser(userList: List<UserEntity>) {
        withContext(Dispatchers.IO) {
            userDao.insertAll(userList)
        }
    }
}