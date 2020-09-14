package com.andrewaprianto.mymovie.core.domain.repository

import com.andrewaprianto.mymovie.core.data.Resource
import com.andrewaprianto.mymovie.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}