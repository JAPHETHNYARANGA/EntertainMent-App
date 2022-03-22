package com.storesoko.entertainmentapp.News.Models

data class newsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)