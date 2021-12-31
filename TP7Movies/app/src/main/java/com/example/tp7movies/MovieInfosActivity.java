package com.example.tp7movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tp7movies.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieInfosActivity extends AppCompatActivity {

    ImageView imageView;
    TextView overView, releaseDate, genreText,name;
    ArrayList<String> genres;
    Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_infos);
        imageView = (ImageView) findViewById(R.id.movie_img);
        overView = (TextView) findViewById(R.id.text_overview);
        releaseDate = (TextView) findViewById(R.id.release_date);
        genreText = (TextView) findViewById(R.id.gender_text_view);
        name=(TextView)findViewById(R.id.name);
        Intent intent = getIntent();
        movie = (Movie) intent.getSerializableExtra("movie");
        genres = intent.getStringArrayListExtra("genres");
        if (movie.getBackdrop_path() != null) {
            Picasso.get().load("https://image.tmdb.org/t/p/original" + movie.getBackdrop_path()).into(imageView);
        } else {
            Picasso.get().load("https://www.seekpng.com/png/detail/94-945337_open-transparent-background-movie-icon.png").into(imageView);
        }
        overView.setText(movie.getOverview()!="" ? movie.getOverview(): "No overview founded");
        name.setText(movie.getTitle());
        releaseDate.setText("Release Date: " +movie.getRelease_date());
        String genresMovies = "";
        for (String genre: genres) {
            genresMovies += " -"+genre + "\n";
        }
        genreText.setText(genresMovies !="" ? genresMovies : "No genres founded");

    }
}