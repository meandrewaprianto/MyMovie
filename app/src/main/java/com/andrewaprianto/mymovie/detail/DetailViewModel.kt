package com.andrewaprianto.mymovie.detail

import androidx.lifecycle.ViewModel
import com.andrewaprianto.mymovie.core.domain.model.Movie
import com.andrewaprianto.mymovie.core.domain.usecase.MovieUseCase

class DetailViewModel (private val movieUseCase: MovieUseCase): ViewModel(){
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}