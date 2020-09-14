package com.andrewaprianto.mymovie.favorite.di

import com.andrewaprianto.mymovie.favorite.FavoriteMovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteMovieModule = module {
    viewModel { FavoriteMovieViewModel(get()) }
}