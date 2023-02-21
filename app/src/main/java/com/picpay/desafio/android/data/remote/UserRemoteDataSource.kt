package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.network.response.UserResponse
import retrofit2.Response

interface UserRemoteDataSource {

    suspend fun getUser(): Response<List<UserResponse>>
}