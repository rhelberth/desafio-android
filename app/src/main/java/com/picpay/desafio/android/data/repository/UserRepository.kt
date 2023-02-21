package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.ResultStatus

interface UserRepository {

    suspend fun getUser(): ResultStatus<List<User>>
}