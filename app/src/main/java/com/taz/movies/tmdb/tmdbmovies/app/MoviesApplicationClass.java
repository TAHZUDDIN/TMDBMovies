package com.taz.movies.tmdb.tmdbmovies.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


public class MoviesApplicationClass extends Application{


    private static Context context;
    public static SharedPreferences commonSharedPreference;

    @Override
    public void onCreate() {
        super.onCreate();
        MoviesApplicationClass.context = getApplicationContext();

    }



    public static Context getContext() {
        return context;
    }

    public static SharedPreferences getCommonSharedPreference() {
        return commonSharedPreference;
    }

}
