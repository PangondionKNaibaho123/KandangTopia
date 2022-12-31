package com.example.kandangtopia.module

import com.example.kandangtopia.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module{
    viewModel { HomeViewModel(get()) }

    single { createGetKandangUseCase(get()) }

    single { createPostRepository(get()) }
}