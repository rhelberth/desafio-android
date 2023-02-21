package com.picpay.desafio.android.data.ui.viewmodel

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetUserUseCase
import com.picpay.desafio.android.ui.ResultStatus
import com.picpay.desafio.android.ui.viewmodel.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class HomeViewModelTest{

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val useCase = mockk<GetUserUseCase>()
    private val viewModel = HomeViewModel(useCase)



    @Test
    fun `when result is loading test pass ok`() = runBlocking {
        coEvery { useCase() } returns ResultStatus.Loading

        viewModel.getUsers()

        viewModel.resultStatus.observeForever {
            when (it) {
                is ResultStatus.Loading -> {
                    assertEquals(ResultStatus.Loading,it)
                }
                is ResultStatus.Success -> {
                    assertEquals(ResultStatus.Success(it.data),it)
                }
                is ResultStatus.Error -> {
                    assertEquals(ResultStatus.Error(it.throwable),it)
                }
            }
        }
    }

    @Test
    fun `when not loading and list user success resultstatus success`() = runBlocking {
        val users = listOf(
            User(img = "img", name = "name", id = 1, username = "username")
        )
        coEvery { useCase() } returns ResultStatus.Success(users)

        viewModel.getUsers()

        viewModel.resultStatus.observeForever {
            when (it) {
                is ResultStatus.Loading -> {
                    assertEquals(ResultStatus.Loading,it)
                }
                is ResultStatus.Success -> {
                    assertEquals(ResultStatus.Success(it.data),it)
                }
                is ResultStatus.Error -> {
                    assertEquals(ResultStatus.Error(it.throwable),it)
                }
            }
        }
    }


    @Test
    fun `when not loading and get users throws exception`() = runBlocking {
        coEvery { useCase() } returns ResultStatus.Error(Throwable())

        viewModel.getUsers()

        viewModel.resultStatus.observeForever {
            when (it) {
                is ResultStatus.Loading -> {
                    assertEquals(ResultStatus.Loading,it)
                }
                is ResultStatus.Success -> {
                    assertEquals(ResultStatus.Success(it.data),it)
                }
                is ResultStatus.Error -> {
                    assertEquals(ResultStatus.Error(it.throwable),it)
                }
            }
        }
    }
}
