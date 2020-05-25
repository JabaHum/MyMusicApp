package com.example.myfmradio.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfmradio.R;
import com.example.myfmradio.common.Config;
import com.example.myfmradio.models.TopTracksData;
import com.example.myfmradio.network.APIClient;
import com.example.myfmradio.network.APIInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

public class RadioListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_list);

        RecyclerView recyclerView = findViewById(R.id.radio_recycler_view);
        fetchTracksData();
    }

    private void fetchTracksData() {
        Retrofit retrofit = APIClient.getClient();

        APIInterface apiInterface = retrofit.create(APIInterface.class);

        Call<JsonObject> call = apiInterface.getTopTracks(Config.toptracks,Config.api_key,Config.format);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NotNull Call<JsonObject> call, @NotNull Response<JsonObject> response) {

               Timber.d("data%s",response.toString());

            }

            @Override
            public void onFailure(@NotNull Call<JsonObject> call, @NotNull Throwable t) {
            }
        });



    }
}
