package com.example.tp7movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    List<Movie> searchedMovies =new ArrayList<>();
    List<Genre> genreList = new ArrayList<>();
    RecyclerView rvMovie ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FetchMovies movieService = new Retrofit.Builder()
                .baseUrl(FetchMovies.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FetchMovies.class);
        movieService.genreList().enqueue(new Callback<GenreList>() {
            @Override
            public void onResponse(Call<GenreList> call, Response<GenreList> response) {
                genreList = response.body().getGenres();
            }
            @Override
            public void onFailure(Call<GenreList> call, Throwable t) {

            }
        });
        movieService.upcomingMovies().enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                upcomingMovies = response.body().getResults();
            }
            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
            }
        });

        movieService.popularMovies().enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                rvMovie = (RecyclerView) findViewById(R.id.rvMovies);
                popularMovies = response.body().getResults();
                MovieAdapter adapter = new MovieAdapter(popularMovies, genreList);
                rvMovie.setAdapter(adapter);
                rvMovie.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            }
            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.popular:{
                                Toast.makeText(getApplicationContext(),"Popular movies",Toast.LENGTH_LONG).show();
                                LinearLayout ln=(LinearLayout)findViewById(R.id.form_search);
                                ln.setVisibility(View.GONE);
                                MovieAdapter mAdapter = new MovieAdapter(popularMovies, genreList);
                                rvMovie.setAdapter(mAdapter);

                                return true;
                            }
                            case R.id.upcoming: {
                                Toast.makeText(getApplicationContext(), "Upcoming movies", Toast.LENGTH_LONG).show();
                                LinearLayout ln=(LinearLayout)findViewById(R.id.form_search);
                                ln.setVisibility(View.GONE);
                                MovieAdapter mAdapter = new MovieAdapter(upcomingMovies, genreList);
                                rvMovie.setAdapter(mAdapter);
                                return true;
                            }
                            case R.id.search: {
                                Toast.makeText(getApplicationContext(), "Search for movies", Toast.LENGTH_LONG).show();
                                LinearLayout ln=(LinearLayout)findViewById(R.id.form_search);
                                ln.setVisibility(View.VISIBLE);
                                MovieAdapter mAdapter = new MovieAdapter(new ArrayList<>(),new ArrayList<>());
                                rvMovie.setAdapter(mAdapter);
                                Button button = (Button) findViewById(R.id.form_botton);
                                EditText movieName = findViewById(R.id.form_text);
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        movieService.searchMovies(movieName.getText().toString()).enqueue(new Callback<MoviesList>() {
                                            @Override
                                            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                                                rvMovie = (RecyclerView) findViewById(R.id.rvMovies);
                                                searchedMovies = response.body().getResults();
                                                MovieAdapter mAdapter = new MovieAdapter(searchedMovies, genreList);
                                                rvMovie.setAdapter(mAdapter);
                                            }
                                            @Override
                                            public void onFailure(Call<MoviesList> call, Throwable t) {
                                            }
                                        });
                                       movieName.setText("");
                                    }
                                });
                                return true;
                            }
                            default:
                                return false;
                        }
                    }
                });
    }

    public void fetchPopularMovies(int page, List<Movie> movies) {
        FetchMovies movieService = new Retrofit.Builder()
                .baseUrl(FetchMovies.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FetchMovies.class);
    }

}