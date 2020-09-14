package com.andrewaprianto.mymovie.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log

import com.andrewaprianto.mymovie.core.data.source.remote.network.ApiResponse
import com.andrewaprianto.mymovie.core.data.source.remote.network.ApiService
import com.andrewaprianto.mymovie.core.data.source.remote.response.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val apiService: ApiService){
    companion object{
        private const val accessToken: String = "9f5f1d2de4366e6ac41ef98f31be6b4b"
    }

    @SuppressLint("CheckResult")
    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieApi>>> {
        //get data from remote api
        return flow {
            try{
                val response = apiService.getPopularMovie(accessToken)
                val dataArray = response.results

                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}