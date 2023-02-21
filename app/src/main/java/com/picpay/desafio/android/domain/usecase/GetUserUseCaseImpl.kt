package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.ResultStatus
import javax.inject.Inject


class GetUserUseCaseImpl
@Inject constructor
    (
    private val userRepository: UserRepository
) : GetUserUseCase {
    override suspend fun invoke(): ResultStatus<List<User>> {
        return try {
            userRepository.getUser()

        } catch (exception: Exception) {
            ResultStatus.Error(exception)
        }
    }
}