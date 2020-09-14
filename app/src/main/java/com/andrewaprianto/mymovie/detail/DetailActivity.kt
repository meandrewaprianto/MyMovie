package com.andrewaprianto.mymovie.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.andrewaprianto.mymovie.R
import com.andrewaprianto.mymovie.core.domain.model.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.qualifier.named

class DetailActivity : AppCompatActivity() {

    private val viewModelDetailScope = getKoin().getOrCreateScope("ScopeDetail", named("Movie"))
    private val detailViewModel: DetailViewModel by viewModelDetailScope.viewModel(this)

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(movie)
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailMovie(movie: Movie?){
        movie?.let {
            collapsing_toolbar.title = movie.title
            setSupportActionBar(toolbar)

            collapsing_toolbar.setCollapsedTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
            collapsing_toolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            txtTitle.text =  movie.title
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500/" + movie.posterPath)
                .into(imgPoster)
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w1280/" +movie.backdropPath)
                .into(imgBackdrop)
            val releaseDate = getString(R.string.releaseDate)
            txtDate.text = releaseDate + movie.releaseDate
            txtDescription.text = movie.overview

            var statusFavorite = movie.isFavorite
            setStatusFavorite(statusFavorite)
            fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(movie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean){
        if (statusFavorite){
            fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_red))
        } else {
            fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}