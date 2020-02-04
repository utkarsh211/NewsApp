package com.utkarsh.newsapp.Models;

public class Article {
    private String urlToImage;
    private String title;
    private String description;
    private String author;
    private String publishedAt;
    private String url;

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}
