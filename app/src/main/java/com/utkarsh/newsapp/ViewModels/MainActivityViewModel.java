package com.utkarsh.newsapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.utkarsh.newsapp.Models.News;
import com.utkarsh.newsapp.Repositories.MainActivityRepository;

public class MainActivityViewModel extends AndroidViewModel {
    public LiveData<News> newsLiveData;
    private MainActivityRepository mainActivityRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mainActivityRepository = MainActivityRepository.getInstance();
    }
    public void getNews()
    {
        newsLiveData = mainActivityRepository.getNews();
    }
}
