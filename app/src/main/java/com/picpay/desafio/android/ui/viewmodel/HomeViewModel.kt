package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetUserUseCase
import com.picpay.desafio.android.ui.ResultStatus
import com.picpay.desafio.android.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor
    (private val getUserUseCase: GetUserUseCase) :
    BaseViewModel<ResultStatus<List<User>>>() {

    init {
        getUsers()
    }

    fun getUsers() {

        viewModelScope.launch {
            resultStatus.value = ResultStatus.Loading
            try {
                resultStatus.value = getUserUseCase()
            } catch (e: Exception) {
                resultStatus.value = ResultStatus.Error(e)
            }
        }

    }
}