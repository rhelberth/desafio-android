package com.picpay.desafio.android.domain.usecase.di

import com.picpay.desafio.android.domain.usecase.GetUserUseCase
import com.picpay.desafio.android.domain.usecase.GetUserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    fun bindGetUser(getUserUseCaseImpl: GetUserUseCaseImpl): GetUserUseCase
}