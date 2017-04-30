package com.example.melod.myapplication.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.melod.myapplication.R;
import com.example.melod.myapplication.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.button) Button submitButton;

    public static final String TAG = "YOUR CHOICE IS :";

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        final Spinner spinner =(Spinner) findViewById(R.id.spinner);




        switch(spinner.getSelectedItem().toString()){
            case "Comedy":
                id="35";
                break;
            case "Adventure":
                id="12";
                break;
            case "Action" :
                id="28";
                break;
            case "Animation":
                id="16";
                break;
            case "Drama":
                id="18";
                break;
            case "Fantasy":
                id="14";
                break;
            case "Horror":
                id="27";
                break;

        }



        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                                     .url("https://api.themoviedb.org/3/genre/"+id+"/movies?" +
                                             "api_key=90ac0136a33bbfed7bca51849672683a&" +
                                             "language=en-US&include_adult=true&sort_by=created_at.asc")
                                     .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String jsonData = response.body().string();
                    try {
                        final Movie[] movies = setDetails(jsonData);

                    submitButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this,MovieActivity.class);
                                intent.putExtra(getString(R.string.key), movies);
                                startActivity(intent);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }
        });


    }

    private Movie[] setDetails(String jsonData) throws JSONException{
        JSONObject object = new JSONObject(jsonData);
        JSONArray result = object.getJSONArray("results");

        Movie[] movies = new Movie[result.length()];

        for(int i=0;i<result.length();i++){
            JSONObject rawMovie = result.getJSONObject(i);

            Movie movie = new Movie();
            movie.setTitle(rawMovie.getString("title"));
            movie.setVoteCount(rawMovie.getInt("vote_count"));
            movie.setVoteAverage(rawMovie.getDouble("vote_average"));

            movies[i] = movie;
        }
        return movies;
    }

    @Override
    protected void onResume(){
        super.onResume();

        

        Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
    }
}

