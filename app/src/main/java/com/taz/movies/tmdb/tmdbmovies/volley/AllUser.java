package com.taz.movies.tmdb.tmdbmovies.volley;

import android.content.Context;

import com.android.volley.Request;


public class AllUser extends BaseObject implements ConstantsRequestIDs {

    private static AllUser sInstance;

    @Override
    public void clear(Context context) {

    }

    public static AllUser getInstance() {
        if (sInstance == null)
            sInstance = new AllUser();
        return sInstance;
    }

    public void moviesGET(String url, AppRequestListener appRequestListener, Context context) {
        AppNetworkError appNetworkError = new AppNetworkError();
        GetMovies request = new GetMovies(Request.Method.POST, url, appNetworkError, ConstantsRequestIDs.ID_GET_MOVIES, appRequestListener);
        sendRequest(context, appNetworkError, request, appRequestListener);
    }


    public void moviesDetailsGET(String url, AppRequestListener appRequestListener, Context context) {
        AppNetworkError appNetworkError = new AppNetworkError();
        GetMoviesDetails request = new GetMoviesDetails(Request.Method.GET, url, appNetworkError, ConstantsRequestIDs.ID_GET_MOVIES_DETAILS, appRequestListener);
        sendRequest(context, appNetworkError, request, appRequestListener);
    }





}
