package com.andrewaprianto.mymovie.core.data

import com.andrewaprianto.mymovie.core.data.source.local.LocalDataSource
import com.andrewaprianto.mymovie.core.data.source.remote.RemoteDataSource
import com.andrewaprianto.mymovie.core.data.source.remote.network.ApiResponse
import com.andrewaprianto.mymovie.core.data.source.remote.response.MovieApi
import com.andrewaprianto.mymovie.core.domain.model.Movie
import com.andrewaprianto.mymovie.core.domain.repository.IMovieRepository
import com.andrewaprianto.mymovie.core.utils.AppExecutors
import com.andrewaprianto.mymovie.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository  constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object: NetworkBoundResource<List<Movie>, List<MovieApi>>(){
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
//                data == null || data.isEmpty()
                true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieApi>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieApi>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntities(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state)}
    }

}