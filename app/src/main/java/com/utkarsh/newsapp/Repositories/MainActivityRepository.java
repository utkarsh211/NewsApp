package com.utkarsh.newsapp.Repositories;

import androidx.lifecycle.MutableLiveData;

import com.utkarsh.newsapp.Models.News;
import com.utkarsh.newsapp.NetworkService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityRepository {
    private static MainActivityRepository instance;
    private static final String newsApi = "d2b15b04bb8d495fba7cee3aeb4ea36d";
    private static final String sources = "google-news";
    private static final String news_url = "https://newsapi.org/v2/";

    public static MainActivityRepository getInstance()
    {
        if(instance == null)
        {
            instance = new MainActivityRepository();
        }
        return instance;
    }
    public MutableLiveData<News> getNews()
    {
        MutableLiveData<News> newsMutableLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .build();
                return chain.proceed(newRequest);
            }
        }).addInterceptor(interceptor)
                .build();
        Retrofit.Builder builder = new Retrofit.Builder()
                .client(client)
                .baseUrl(news_url)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        NetworkService networkService = retrofit.create(NetworkService.class);
        Call<News> call = networkService.getNews(sources, newsApi);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                News news = response.body();
                newsMutableLiveData.setValue(news);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
        return newsMutableLiveData;
    }
}
