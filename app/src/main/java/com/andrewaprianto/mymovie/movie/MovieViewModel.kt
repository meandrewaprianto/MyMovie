package com.andrewaprianto.mymovie.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andrewaprianto.mymovie.core.domain.usecase.MovieUseCase

class MovieViewModel (movieUseCase: MovieUseCase): ViewModel(){
    val movie = movieUseCase.getAllMovie().asLiveData()
}