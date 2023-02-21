package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.ResultStatus

interface GetUserUseCase {

    suspend operator fun invoke(): ResultStatus<List<User>>

}