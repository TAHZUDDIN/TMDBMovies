package com.taz.movies.tmdb.tmdbmovies.volley;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.taz.movies.tmdb.tmdbmovies.model.Movies;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class GetMovies extends BaseTask<JSONObject> {

    public String TAG = "GetMovies";

    public GetMovies(int method, String url, Response.ErrorListener listener, String requestTag, AppRequestListener requestListener) {
        super(method, url, listener, requestTag, requestListener);
        headers = new HashMap<String, String>();
    }

    @Override
    public void processData() {
        sendMessage();
    }


    Movies movies;


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        statusCode = response.statusCode;
        String responseString = new String(response.data);
        Log.i(TAG, "response:" + responseString);
        movies = new Gson().fromJson(responseString, Movies.class);
        return Response.success(
                JSONUtils.getJSONObject(responseString),
                getCacheEntry());
    }


    public Movies getDataObject() {
        return movies;
    }


    @Override
    protected void deliverResponse(JSONObject response) {

        this.setResponse(response);
        RequestPoolManager.getInstance().executeRequest(this);

    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }
}