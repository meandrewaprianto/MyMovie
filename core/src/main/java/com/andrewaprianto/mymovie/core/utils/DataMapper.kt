package com.andrewaprianto.mymovie.core.utils

import com.andrewaprianto.mymovie.core.data.source.local.entity.MovieEntity
import com.andrewaprianto.mymovie.core.data.source.remote.response.MovieApi
import com.andrewaprianto.mymovie.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieApi>): List<MovieEntity>{
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieID = it.id,
                movieName = it.title,
                movieReleaseDate = it.releaseDate,
                moviePosterPath = it.posterPath,
                movieBackdropPath = it.backdropPath,
                movieOverview = it.overview,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.movieID,
                overview = it.movieOverview,
                title = it.movieName,
                posterPath = it.moviePosterPath,
                backdropPath = it.movieBackdropPath,
                releaseDate = it.movieReleaseDate,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntities(input: Movie) = MovieEntity(
        movieID = input.id,
        movieName = input.title,
        movieOverview = input.overview,
        moviePosterPath = input.posterPath,
        movieBackdropPath = input.backdropPath,
        movieReleaseDate = input.releaseDate,
        isFavorite = input.isFavorite
    )
}