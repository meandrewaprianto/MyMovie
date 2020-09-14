package com.andrewaprianto.mymovie.core

import com.andrewaprianto.mymovie.core.data.Resource
import com.andrewaprianto.mymovie.core.domain.model.Movie
import com.andrewaprianto.mymovie.core.domain.repository.IMovieRepository
import com.andrewaprianto.mymovie.core.domain.usecase.MovieInteractor
import com.andrewaprianto.mymovie.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieUseCaseTest{
    private lateinit var movieUseCase: MovieUseCase

    companion object{
         val MOVIE = Movie(1, "Lorem Ipsum",
            "Mulan", "Lorem", "Lorem", "Lorem", false)
    }

    @Mock
    private lateinit var movieRepository: IMovieRepository

    @Before
    fun setUp(){
        movieUseCase = MovieInteractor(movieRepository)
        val dummyListMovieRespose: Flow<Resource<List<Movie>>> = flow { Resource.Success(listOf(MOVIE)) }

        Mockito.`when`(movieRepository.getAllMovie()).thenReturn(dummyListMovieRespose)
    }

    @Test
    fun `should get list data movie from repository`(){
        movieUseCase.getAllMovie()
        Mockito.verify(movieRepository).getAllMovie()
        Mockito.verifyNoMoreInteractions(movieRepository)
    }
}