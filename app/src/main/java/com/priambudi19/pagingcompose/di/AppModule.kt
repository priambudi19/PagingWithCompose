package com.priambudi19.pagingcompose.di

import com.priambudi19.pagingcompose.data.network.PicsumClient
import com.priambudi19.pagingcompose.data.network.PicsumService
import com.priambudi19.pagingcompose.data.repository.MainRepository
import com.priambudi19.pagingcompose.data.repository.MainRepositoryImpl
import com.priambudi19.pagingcompose.ui.detail.DetailViewModel
import com.priambudi19.pagingcompose.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<PicsumService> {
        PicsumClient()
    }
    single<MainRepository> {
        MainRepositoryImpl(get())
    }
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        DetailViewModel(get())
    }

}