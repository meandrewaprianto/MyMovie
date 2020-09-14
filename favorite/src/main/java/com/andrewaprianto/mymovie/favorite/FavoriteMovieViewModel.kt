package com.andrewaprianto.mymovie.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andrewaprianto.mymovie.core.domain.usecase.MovieUseCase

class FavoriteMovieViewModel(movieUseCase: MovieUseCase): ViewModel(){
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}