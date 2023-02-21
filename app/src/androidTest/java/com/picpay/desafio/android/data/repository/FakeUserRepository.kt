package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.ResultStatus
import java.util.Objects
import javax.inject.Inject

class FakeUserRepository @Inject constructor(): UserRepository {

      lateinit var resultUser: ResultStatus<List<User>>

    override suspend fun getUser(): ResultStatus<List<User>> {
        return resultUser
    }
}