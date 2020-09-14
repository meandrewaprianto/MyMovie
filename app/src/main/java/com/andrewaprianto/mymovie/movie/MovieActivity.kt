package com.andrewaprianto.mymovie.movie

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.andrewaprianto.mymovie.R
import com.andrewaprianto.mymovie.core.data.Resource
import com.andrewaprianto.mymovie.core.ui.MovieAdapter
import com.andrewaprianto.mymovie.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.fabFavorite
import kotlinx.android.synthetic.main.activity_main.progress_bar
import kotlinx.android.synthetic.main.activity_main.rv_movie
import kotlinx.android.synthetic.main.activity_main.view_error
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.qualifier.named

class MovieActivity : AppCompatActivity() {

    private val viewModelMovieScope = getKoin().getOrCreateScope("ScopeMovieList", named("Movie"))
    private val movieViewModel: MovieViewModel by viewModelMovieScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        movieViewModel.movie.observe(this, { movie ->
            if(movie != null){
                when(movie){
                    is Resource.Loading -> {
                        progress_bar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        progress_bar.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        progress_bar.visibility = View.GONE
                        view_error.visibility = View.VISIBLE
                    }
                }
            }
        })

        with(rv_movie){
            layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        fabFavorite.setOnClickListener {
            val uri = Uri.parse("mymovie://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelMovieScope.close()
    }
}