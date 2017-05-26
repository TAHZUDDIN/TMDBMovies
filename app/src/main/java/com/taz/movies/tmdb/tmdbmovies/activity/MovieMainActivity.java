package com.taz.movies.tmdb.tmdbmovies.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.taz.movies.tmdb.tmdbmovies.R;
import com.taz.movies.tmdb.tmdbmovies.adapter.MoviesAdapter;
import com.taz.movies.tmdb.tmdbmovies.model.Movies;
import com.taz.movies.tmdb.tmdbmovies.model.StartActivityListener;
import com.taz.movies.tmdb.tmdbmovies.util.Constants;
import com.taz.movies.tmdb.tmdbmovies.volley.AllUser;
import com.taz.movies.tmdb.tmdbmovies.volley.AppRequestListener;
import com.taz.movies.tmdb.tmdbmovies.volley.BaseTask;
import com.taz.movies.tmdb.tmdbmovies.volley.GetMovies;

public class MovieMainActivity extends AppCompatActivity implements AppRequestListener, StartActivityListener {

    Toolbar toolbar;
    Movies movies;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerViewMovies;
    MoviesAdapter moviesAdapter;
    RelativeLayout Rl_ProgressBar;
    CoordinatorLayout parent_coordinatorLayout;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerViewMovies = (RecyclerView)findViewById(R.id.id_recyclerView);
        Rl_ProgressBar = (RelativeLayout)findViewById(R.id.id_RL_progressbar);
        parent_coordinatorLayout =(CoordinatorLayout)findViewById(R.id.id_coordinatorLayout);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.id_swipeRefreshLayout);
        callAPI();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                callAPI();
            }
        });
    }



    public void callAPI(){
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=d66d39af93971546a6280262deef40d6";
        AllUser.getInstance().moviesGET(url, this, this);
    }




    @Override
    public <T> void onRequestStarted(BaseTask<T> request) {
        VisibilityOnOff(false);
    }


    @Override
    public <T> void onRequestCompleted(BaseTask<T> request) {
        if (((GetMovies)request).getDataObject() != null) {
            movies = ((GetMovies)request).getDataObject();
            initAdapterAndCall();
            // Stop refresh animation
            swipeRefreshLayout.setRefreshing(false);
            VisibilityOnOff(true);
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
        Intent i = new Intent(this,MoviesDetailsActivity.class);
        i.putExtra(Constants.MOVIE, movie);
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }



    public void VisibilityOnOff(boolean CoordinatorTrueOrFalse ){
            if(CoordinatorTrueOrFalse == true){
                parent_coordinatorLayout.setVisibility(View.VISIBLE);
                Rl_ProgressBar.setVisibility(View.GONE);
            }
            else {
                parent_coordinatorLayout.setVisibility(View.GONE);
                Rl_ProgressBar.setVisibility(View.VISIBLE);
            }
    }


}
