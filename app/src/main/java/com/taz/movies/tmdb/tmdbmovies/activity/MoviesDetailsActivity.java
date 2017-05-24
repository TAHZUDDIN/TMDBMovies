package com.taz.movies.tmdb.tmdbmovies.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.taz.movies.tmdb.tmdbmovies.R;
import com.taz.movies.tmdb.tmdbmovies.pojo.Movies;
import com.taz.movies.tmdb.tmdbmovies.util.Constants;

public class MoviesDetailsActivity extends AppCompatActivity {

    Movies.Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);


        if (getIntent().getSerializableExtra(Constants.MOVIE) != null)
            movie = (Movies.Movie) getIntent().getSerializableExtra(Constants.MOVIE);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
