package com.example.melod.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.melod.myapplication.R;
import com.example.melod.myapplication.model.Movie;

/**
 * Created by Melod on 30.04.2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
       private Movie[] movies ;

    public MovieAdapter(Movie[] movies){
        this.movies = movies;
    }


    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_layout_item,parent,false);
        MovieHolder holder = new MovieHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.bind(movies[position]);
    }

    @Override
    public int getItemCount() {
        return movies.length;
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView averageRating;
        private TextView voteCount;

        public MovieHolder(View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.textView2);
            averageRating = (TextView) itemView.findViewById(R.id.textView3);
            voteCount = (TextView) itemView.findViewById(R.id.textView5);
        }

        public void bind(Movie movie){
            title.setText(movie.getTitle());
            averageRating.setText(movie.getVoteAverage()+"");
            voteCount.setText(movie.getVoteCount()+"");
        }
    }
}
