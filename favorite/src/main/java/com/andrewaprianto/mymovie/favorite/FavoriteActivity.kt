package com.andrewaprianto.mymovie.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.andrewaprianto.mymovie.core.ui.MovieAdapter
import com.andrewaprianto.mymovie.detail.DetailActivity
import com.andrewaprianto.mymovie.favorite.di.favoriteMovieModule
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        loadKoinModules(favoriteMovieModule)

        supportActionBar?.title = "Favorite Movie"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getFavoriteMovie()
    }

    private fun getFavoriteMovie(){
        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteMovieViewModel.favoriteMovie.observe(this, { dataMovie ->
            movieAdapter.setData(dataMovie)
            view_empty.visibility = if (dataMovie.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(rv_favorite){
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}