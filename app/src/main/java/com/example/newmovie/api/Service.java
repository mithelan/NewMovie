package com.example.newmovie.api;

import com.example.newmovie.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

@GET("movie/popular")
Call<MovieResponse> getPopularMovies(@Query("api_key")String api);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key")String api);
}