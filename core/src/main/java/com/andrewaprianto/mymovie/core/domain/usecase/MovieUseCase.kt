package com.andrewaprianto.mymovie.core.domain.usecase

import com.andrewaprianto.mymovie.core.data.Resource
import com.andrewaprianto.mymovie.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase{
    fun getAllMovie(): Flow<Resource<List<Movie>>>//Change to popular movie
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}