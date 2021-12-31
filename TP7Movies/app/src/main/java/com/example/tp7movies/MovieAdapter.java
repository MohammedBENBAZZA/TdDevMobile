package com.example.tp7movies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    private final List<Movie> mMovies;
    private final List<Genre> mGenres;

    public MovieAdapter(List<Movie> movies, List<Genre> genres){
        mMovies = movies;
        mGenres = genres;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context=holder.itemView.getContext();
        Movie movie = mMovies.get(position);
        ImageView imageView = holder.movieImg;
        if (movie.getPoster_path() != null) {
            Glide.with(context).load("https://image.tmdb.org/t/p/original" + movie.getPoster_path()).into(imageView);
        } else {
            Glide.with(context).load("https://www.seekpng.com/png/detail/94-945337_open-transparent-background-movie-icon.png").into(imageView);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MovieInfosActivity.class);
                intent.putExtra("movie", movie);
                intent.putExtra("genres", getGenreById(movie.getGenre_ids(), mGenres));
                view.getContext().startActivity(intent);
            }

            public ArrayList<String> getGenreById(List<Integer> ids, List<Genre> genres) {
                ArrayList<String> genresText = new ArrayList<>();
                for (int n: ids) {
                    for (Genre g:
                            genres) {
                        if(n == g.getId()) {
                            genresText.add(g.getName());
                        }
                    }
                }
                return genresText;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView movieImg;

        public ViewHolder(View itemView){
            super(itemView);
            movieImg = (ImageView) itemView.findViewById(R.id.movie_affiche);
        }
    }
}
