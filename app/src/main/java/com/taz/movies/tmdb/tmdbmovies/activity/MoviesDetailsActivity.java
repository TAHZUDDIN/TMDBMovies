package com.taz.movies.tmdb.tmdbmovies.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taz.movies.tmdb.tmdbmovies.R;
import com.taz.movies.tmdb.tmdbmovies.app.MoviesApplicationClass;
import com.taz.movies.tmdb.tmdbmovies.model.Movies;
import com.taz.movies.tmdb.tmdbmovies.model.MoviesDetails;
import com.taz.movies.tmdb.tmdbmovies.util.Constants;
import com.taz.movies.tmdb.tmdbmovies.volley.AllUser;
import com.taz.movies.tmdb.tmdbmovies.volley.AppRequestListener;
import com.taz.movies.tmdb.tmdbmovies.volley.BaseTask;
import com.taz.movies.tmdb.tmdbmovies.volley.GetMoviesDetails;

public class MoviesDetailsActivity extends AppCompatActivity  implements AppRequestListener{

    Movies.Movie movie;
    MoviesDetails moviesDetails;
    Toolbar toolbar;
    LinearLayout LL_MainLayout;
    RelativeLayout RL_Progressbar;
    ImageView imageView;

    TextView title, releaseDate, runtime , status, budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView=(ImageView)findViewById(R.id.image);
        title =(TextView)findViewById(R.id.title);
        releaseDate = (TextView)findViewById(R.id.release_date);
        runtime = (TextView)findViewById(R.id.runtime);
        status = (TextView)findViewById(R.id.status);
        budget = (TextView)findViewById(R.id.budget);
        LL_MainLayout =(LinearLayout)findViewById(R.id.id_LL_mainLayout);
        RL_Progressbar =(RelativeLayout)findViewById(R.id.id_RL_progressbarDetails);

        if (getIntent().getSerializableExtra(Constants.MOVIE) != null)
            movie = (Movies.Movie) getIntent().getSerializableExtra(Constants.MOVIE);

        if(movie != null)
            callAPI();
    }




    public void callAPI(){
        String url = "https://api.themoviedb.org/3/movie/"+movie.getId()+"?api_key=d66d39af93971546a6280262deef40d6&language=en-US&append_to_response=videos";
        AllUser.getInstance().moviesDetailsGET(url, this, this);
    }



    @Override
    public <T> void onRequestStarted(BaseTask<T> request) {
        VisibilityOnOff(false);
    }



    @Override
    public <T> void onRequestCompleted(BaseTask<T> request) {
        if (((GetMoviesDetails)request).getDataObject() != null) {
            moviesDetails = ((GetMoviesDetails)request).getDataObject();
            setData();
            VisibilityOnOff(true);

            if(moviesDetails.getVideos().getResults()!= null);
                if(moviesDetails.getVideos().getResults().size()==0){
                    setVisibilityGone();
                }
        }
    }



    @Override
    public <T> void onRequestFailed(BaseTask<T> request) {
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }


    public void VisibilityOnOff(boolean mainLayoutTrueOrFalse ){
        if(mainLayoutTrueOrFalse == true){
            LL_MainLayout.setVisibility(View.VISIBLE);
            RL_Progressbar.setVisibility(View.GONE);
        }
        else {
            LL_MainLayout.setVisibility(View.GONE);
            RL_Progressbar.setVisibility(View.VISIBLE);
        }
    }



    public void setData(){

        String imageURl= "https://image.tmdb.org/t/p/w300"+moviesDetails.getBackdrop_path();
        Picasso.with(MoviesApplicationClass.getContext())
                .load(imageURl)
                .placeholder(R.drawable.image_placeholder_details)
                .error(R.drawable.image_placeholder_details)//this is also optional if some error has occurred in downloading the image this image would be displayed
                .into(imageView);

        title.setText(moviesDetails.getTitle());
        releaseDate.setText(moviesDetails.getRelease_date());
        runtime.setText(moviesDetails.getRuntime());
        status.setText(moviesDetails.getStatus());
        budget.setText(moviesDetails.getBudget());
    }


    public void startChatting(View v){
        Intent i = new Intent(this, ChattingActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }



    public void playVideo(View v){
        if(moviesDetails.getVideos().getResults().size()>0){

            Intent i = new Intent(this, YoutubeActivity.class);
            i.putExtra(Constants.YOUTUBE_KEY,moviesDetails.getVideos().getResults().get(0).getKey());
            startActivity(i);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        }
    }


    public void setVisibilityGone(){
        findViewById(R.id.id_play_icon).setVisibility(View.GONE);
    }


}
