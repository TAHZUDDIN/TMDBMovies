package com.taz.movies.tmdb.tmdbmovies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taz.movies.tmdb.tmdbmovies.R;
import com.taz.movies.tmdb.tmdbmovies.app.MoviesApplicationClass;
import com.taz.movies.tmdb.tmdbmovies.pojo.Movies;
import com.taz.movies.tmdb.tmdbmovies.pojo.StartActivityListener;

import java.util.ArrayList;
import java.util.List;




public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> implements View.OnClickListener {


    List<Movies.Movie> movies;


    public StartActivityListener startActivityListener;


    public void setStartActivityListener(StartActivityListener startActivityListener) {
        this.startActivityListener = startActivityListener;
    }


    public MoviesAdapter(List<Movies.Movie> movies) {
        this.movies = new ArrayList<>();
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movies_layout, parent, false); //Inflating the layout
        ViewHolder vhItem = new ViewHolder(v); //Creating ViewHolder and passing the object of type view
        return vhItem;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Movies.Movie movie = movies.get(position);
        String ImageUrl = "https://image.tmdb.org/t/p/w300"+movie.getPoster_path();


        Picasso.with(MoviesApplicationClass.getContext())
                .load(ImageUrl)        //this is also optional if some error has occurred in downloading the image this image would be displayed
                .into(holder.movieImage);


//                .placeholder(Your Drawable Resource)
//                .error(Your Drawable Resource)

        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getOverview());


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

//            case R.id.id_LinLay_parent_inflatingRepo:
//                startActivityListener.startActivityMethod(((UserRepoDetails) view.getTag()), view);
//                break;
//            default:
//                break;
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parentLayout;
        ImageView movieImage;
        TextView title, description;


        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = (LinearLayout) itemView.findViewById(R.id.id_LinLay_parent_inflatingMovies);
            parentLayout.setOnClickListener(MoviesAdapter.this);
            movieImage = (ImageView) itemView.findViewById(R.id.id_image);
            title = (TextView) itemView.findViewById(R.id.id_title);
            description = (TextView) itemView.findViewById(R.id.id_description);

        }
    }


}
