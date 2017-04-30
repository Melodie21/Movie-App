package com.example.melod.myapplication.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.melod.myapplication.R;
import com.example.melod.myapplication.adapters.MovieAdapter;
import com.example.melod.myapplication.model.Movie;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity {

    private Movie[] movies;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(getString(R.string.key));
        movies = Arrays.copyOf(parcelables,parcelables.length,Movie[].class);



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter movieAdapter = new MovieAdapter(movies);
        recyclerView.setAdapter(movieAdapter);

        recyclerView.setHasFixedSize(true);



    }
}
