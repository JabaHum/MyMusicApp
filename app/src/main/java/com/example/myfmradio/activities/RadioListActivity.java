package com.example.myfmradio.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfmradio.R;
import com.example.myfmradio.common.Config;
import com.example.myfmradio.models.TopTracksData;
import com.example.myfmradio.models.TrackData;
import com.example.myfmradio.network.APIClient;
import com.example.myfmradio.network.APIInterface;
import com.google.gson.Gson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;


public class RadioListActivity extends AppCompatActivity {

    List<TrackData> trackData;
    Moshi moshi;
    JsonAdapter<TopTracksData> jsonAdapter;
    RecyclerView rvTopTracks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_list);
        rvTopTracks = findViewById(R.id.radio_recycler_view);



        trackData = new ArrayList<>();



        fetchTracksData();
    }

    private void fetchTracksData() {
        Retrofit retrofit = APIClient.getClient();

        APIInterface apiInterface = retrofit.create(APIInterface.class);

        Call<String> call = apiInterface.getTopTracks(Config.toptracks, Config.api_key, Config.format);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                Timber.d("data%s", response.body());
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        String msg = jsonObject.getString("tracks");
                        Timber.d("data%s", msg);

                        Moshi moshi = new Moshi.Builder().build();
                        JsonAdapter<TopTracksData> jsonAdapter = moshi.adapter(TopTracksData.class);

                        TopTracksData post = jsonAdapter.fromJson(msg);

                        Log.i("dataResponse",post.getTrack().toString());


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {

                }

            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                Timber.d("dataError%s", call.toString());
            }
        });



    }
}
