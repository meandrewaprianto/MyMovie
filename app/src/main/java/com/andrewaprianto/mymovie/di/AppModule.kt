package com.andrewaprianto.mymovie.di

import com.andrewaprianto.mymovie.core.domain.usecase.MovieInteractor
import com.andrewaprianto.mymovie.core.domain.usecase.MovieUseCase
import com.andrewaprianto.mymovie.detail.DetailViewModel
import com.andrewaprianto.mymovie.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    scope(named("Movie")){
        viewModel { MovieViewModel(get()) }
        viewModel { DetailViewModel(get()) }
    }
}