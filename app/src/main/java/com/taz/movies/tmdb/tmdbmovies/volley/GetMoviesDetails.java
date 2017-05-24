package com.taz.movies.tmdb.tmdbmovies.volley;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.taz.movies.tmdb.tmdbmovies.pojo.MoviesDetails;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tahzuddin on 24/05/17.
 */

public class GetMoviesDetails  extends BaseTask<JSONObject> {

    public String TAG = "GetMoviesDetails";

    public GetMoviesDetails(int method, String url, Response.ErrorListener listener, String requestTag, AppRequestListener requestListener) {
        super(method, url, listener, requestTag, requestListener);
        headers = new HashMap<String, String>();
    }

    @Override
    public void processData() {
        sendMessage();
    }


    MoviesDetails moviesDetails;


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        statusCode = response.statusCode;
        String responseString = new String(response.data);
        Log.i(TAG, "response:" + responseString);
        moviesDetails = new Gson().fromJson(responseString, MoviesDetails.class);
        return Response.success(
                JSONUtils.getJSONObject(responseString),
                getCacheEntry());
    }


    public MoviesDetails getDataObject() {
        return moviesDetails;
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
