package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.network.PicPayService
import com.picpay.desafio.android.data.network.response.UserResponse
import retrofit2.Response
import javax.inject.Inject


class RetrofitUserRemoteDataSource @Inject constructor(
    private val picPayService: PicPayService
) : UserRemoteDataSource {

    override suspend fun getUser(): Response<List<UserResponse>> {
        return picPayService.getUsers()
    }
}