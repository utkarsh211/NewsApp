package com.utkarsh.newsapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {
    @SerializedName("articles")
    @Expose
    private List<Article> articlesArrayList;

    public List<Article> getArticlesArrayList() {
        return articlesArrayList;
    }
}
