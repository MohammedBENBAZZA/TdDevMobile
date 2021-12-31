package com.example.tp7movies;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FetchMovies {

    public static final String ENDPOINT = "https://api.themoviedb.org/3/";

    public static final String apiKey = "885c00a12fc549d2091e52bfcac7f3f4";

    @GET("movie/popular?api_key="+apiKey+"&language=en-US")
    Call<MoviesList> popularMovies();

    @GET("movie/upcoming?api_key="+apiKey+"&language=en-US")
    Call<MoviesList> upcomingMovies();

    @GET("genre/movie/list?api_key="+apiKey+"&language=en-US")
    Call<GenreList> genreList();
    @GET("search/movie?api_key="+apiKey)
    Call<MoviesList> searchMovies(@Query("query") String query);
}
