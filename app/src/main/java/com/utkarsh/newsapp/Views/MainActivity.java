package com.utkarsh.newsapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.utkarsh.newsapp.Models.Article;
import com.utkarsh.newsapp.Models.News;
import com.utkarsh.newsapp.R;
import com.utkarsh.newsapp.ViewModels.MainActivityViewModel;
import com.utkarsh.newsapp.Views.Adapters.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnCardClickListener {
    private RecyclerView recyclerView;
    private List<Article> articlesList;
    private RecyclerAdapter recyclerAdapter;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        articlesList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        initAuthViewModel();
        mainActivityViewModel.getNews();
        mainActivityViewModel.newsLiveData.observe(this, new Observer<News>() {
            @Override
            public void onChanged(News news) {
                articlesList = news.getArticlesArrayList();
                recyclerAdapter = new RecyclerAdapter(MainActivity.this, articlesList, MainActivity.this);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });

    }
    private void initAuthViewModel()
    {
       mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }

    @Override
    public void onCardClick(int position) {
        Intent intent = new Intent(MainActivity.this, NewsPageActivity.class);
        intent.putExtra("newsUrl",articlesList.get(position).getUrl());
        startActivity(intent);
    }
}
