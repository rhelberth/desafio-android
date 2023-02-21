package com.picpay.desafio.android.data.repository

import android.util.Log
import com.picpay.desafio.android.data.local.UserLocalDataSource
import com.picpay.desafio.android.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.data.network.response.toDomain
import com.picpay.desafio.android.data.network.response.toEntity
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.ResultStatus
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl
@Inject constructor
    (
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource

) : UserRepository {

    override suspend fun getUser(): ResultStatus<List<User>> {

        try {
            val response = userRemoteDataSource.getUser()
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    userLocalDataSource.saveAllUser(result.toEntity())
                  //  Log.i("TAG", "ONLINE")
                    return ResultStatus.Success(result.toDomain())
                }
            } else {
              //  Log.i("TAG", "OFFLINE")
                return ResultStatus.Success(userLocalDataSource.getAllUser())
            }
        } catch (ioException: IOException) {
         //   Log.i("TAG", "OFFLINE ERRO")
            return ResultStatus.Success(userLocalDataSource.getAllUser())
        }
        return ResultStatus.Error(Exception())
    }

}