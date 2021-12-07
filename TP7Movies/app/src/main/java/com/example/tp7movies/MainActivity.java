package com.example.tp7movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<Movie> popularMovies= new ArrayList<>();
    List<Movie> upcomingMovies =new ArrayList<>();
    List<Genre> genreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView rvMovie;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchMovies movieService = new Retrofit.Builder()
                .baseUrl(fetchMovies.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(fetchMovies.class);
        BottomNavigationView BottomNavigation=(BottomNavigationView) findViewById(R.id.bottomnavigation);

        movieService.genreList().enqueue(new Callback<GenreList>() {
            @Override
            public void onResponse(Call<GenreList> call, Response<GenreList> response) {
                genreList = response.body().getGenres();
                System.out.println("result2222222"+ genreList.toString());
            }

            @Override
            public void onFailure(Call<GenreList> call, Throwable t) {

            }
        });
        movieService.upcomingMovies().enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                RecyclerView rvMovie = (RecyclerView) findViewById(R.id.rvMovies);
                upcomingMovies = response.body().getResults();
                MovieAdapter adapter = new MovieAdapter(upcomingMovies, genreList);
                rvMovie.setAdapter(adapter);
                rvMovie.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            }





























            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {

            }
        });
        movieService.listMovies().enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                popularMovies = response.body().getResults();

            }
            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {

            }
        });


    }
}