package com.example.moviereviewapplication

class MovieClass(var name: String, var desc: String, var language: String, var releaseDate: String,var suitable: String, var reason1: Boolean, var reason2: Boolean, var review: Float?) {
    init {
        this.name = name;
        this.desc = desc;
        this.language = language;
        this.releaseDate = releaseDate;
        this.suitable = suitable;
        this.reason1 = reason1;
        this.reason2 = reason2;
        this.review = review;
    }
}