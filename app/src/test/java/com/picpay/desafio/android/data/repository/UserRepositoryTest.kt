package com.picpay.desafio.android.data.repository

import com.google.common.truth.Truth.assertThat
import com.picpay.desafio.android.data.local.UserLocalDataSource
import com.picpay.desafio.android.data.network.response.UserResponse
import com.picpay.desafio.android.data.network.response.toDomain
import com.picpay.desafio.android.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.ui.ResultStatus
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException


class UserRepositoryTest {

    @MockK(relaxed = true)
    lateinit var mockLocalDataSource: UserLocalDataSource

    @MockK
    lateinit var mockRemoteDataSource: UserRemoteDataSource

    private lateinit var repository: UserRepositoryImpl
    private val userResponse =
        listOf(UserResponse(id = 0, name = "name", username = "username", img = "img"))
    private val user = userResponse.toDomain()


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = UserRepositoryImpl(
            userRemoteDataSource = mockRemoteDataSource,
            userLocalDataSource = mockLocalDataSource
        )
    }

    @Test
    fun `When data provides from remote return dataSuccess from remote`() {

        val expected = Response.success(userResponse)
        coEvery { mockRemoteDataSource.getUser() } returns expected
        val result = runBlocking { (repository.getUser()) }
        assertThat(result).isEqualTo(ResultStatus.Success(expected.body()?.toDomain()))
    }


    @Test
    fun `When remote throws exception, get data from local cache`() {

        coEvery { mockRemoteDataSource.getUser() } throws IOException()
        coEvery { mockLocalDataSource.getAllUser() } returns user
        val result = runBlocking { repository.getUser() }
        assertThat(result).isEqualTo(ResultStatus.Success(user))

    }
}