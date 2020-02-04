package com.utkarsh.newsapp;

import com.utkarsh.newsapp.Models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkService {
    @GET("top-headlines")
    Call<News> getNews(@Query("sources") String sources, @Query("apiKey") String apiKey);
}
