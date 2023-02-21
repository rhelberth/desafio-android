package com.picpay.desafio.android.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<T> : ViewModel() {

    fun resultStatus(): LiveData<T> = resultStatus
    val resultStatus: MutableLiveData<T> = MutableLiveData()
}