package com.example.tp7movies;


import retrofit2.Call;
import retrofit2.http.GET;

public interface fetchMovies {

    public static final String ENDPOINT = "https://api.themoviedb.org/3/";

    public static final String apiKey = "885c00a12fc549d2091e52bfcac7f3f4";

    @GET("movie/popular?api_key="+apiKey+"&language=en-US&page=1")
    Call<MoviesList> listMovies();

    @GET("movie/upcoming?api_key="+apiKey+"&language=en-US&page=1")
    Call<MoviesList> upcomingMovies();

    @GET("genre/movie/list?api_key="+apiKey+"&language=en-US")
    Call<GenreList> genreList();
}
