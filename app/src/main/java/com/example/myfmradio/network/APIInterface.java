package com.example.myfmradio.network;

import com.example.myfmradio.models.TopTracksData;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/2.0/")
    Call<String> getTopTracks(@Query("method") String toptracks,
                              @Query("api_key") String api_key,
                              @Query("format") String format);


    // @GET("?method=artist.search&format=json&api_key=" + API_KEY)
}
