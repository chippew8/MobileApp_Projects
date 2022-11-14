package com.example.moviereviewapplication

class MovieClass(var name: String, var desc: String, var language: String, var releaseDate: String, var suitable: String) {
    init {
        this.name = name;
        this.desc = desc;
        this.language = language;
        this.suitable = suitable;
    }
}