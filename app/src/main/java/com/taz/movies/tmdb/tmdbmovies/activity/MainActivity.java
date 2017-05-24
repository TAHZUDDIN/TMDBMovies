package com.taz.movies.tmdb.tmdbmovies.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.taz.movies.tmdb.tmdbmovies.R;
import com.taz.movies.tmdb.tmdbmovies.adapter.MoviesAdapter;
import com.taz.movies.tmdb.tmdbmovies.pojo.Movies;
import com.taz.movies.tmdb.tmdbmovies.pojo.StartActivityListener;
import com.taz.movies.tmdb.tmdbmovies.volley.AllUser;
import com.taz.movies.tmdb.tmdbmovies.volley.AppRequestListener;
import com.taz.movies.tmdb.tmdbmovies.volley.BaseTask;
import com.taz.movies.tmdb.tmdbmovies.volley.GetMovies;

public class MainActivity extends AppCompatActivity implements AppRequestListener, StartActivityListener {

    Toolbar toolbar;
    Movies movies;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerViewMovies;
    MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerViewMovies = (RecyclerView)findViewById(R.id.id_recyclerView);

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=d66d39af93971546a6280262deef40d6";
        AllUser.getInstance().moviesGET(url, this, this);
    }





    @Override
    public <T> void onRequestStarted(BaseTask<T> request) {



    }

    @Override
    public <T> void onRequestCompleted(BaseTask<T> request) {

        if (((GetMovies)request).getDataObject() != null) {

            movies = ((GetMovies)request).getDataObject();
            initAdapterAndCall();

        }

    }

    @Override
    public <T> void onRequestFailed(BaseTask<T> request) {

    }


    public void initAdapterAndCall() {

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMovies.setLayoutManager(linearLayoutManager);
        moviesAdapter = new MoviesAdapter(movies.getResults());
        moviesAdapter.setStartActivityListener(this);
        recyclerViewMovies.setAdapter(moviesAdapter);

    }

    @Override
    public void startActivityMethod(Movies.Movie movie) {

    }


}
