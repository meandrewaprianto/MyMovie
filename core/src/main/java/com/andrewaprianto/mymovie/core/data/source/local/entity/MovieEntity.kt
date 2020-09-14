package com.andrewaprianto.mymovie.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieID")
    var movieID: Int,

    @ColumnInfo(name = "movieName")
    var movieName: String,

    @ColumnInfo(name = "movieReleaseDate")
    var movieReleaseDate: String,

    @ColumnInfo(name = "moviePosterPath")
    var moviePosterPath: String,

    @ColumnInfo(name = "movieBackdropPath")
    var movieBackdropPath: String,

    @ColumnInfo(name = "movieOverview")
    var movieOverview: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
): Parcelable