package com.priambudi19.pagingcompose.di

import com.priambudi19.pagingcompose.data.network.ApiClient
import com.priambudi19.pagingcompose.data.repository.MainRepository
import com.priambudi19.pagingcompose.data.repository.MainRepositoryImpl
import com.priambudi19.pagingcompose.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        ApiClient.getInstance()
    }
    single<MainRepository> {
        MainRepositoryImpl(get())
    }
    viewModel {
        MainViewModel(get())
    }
}