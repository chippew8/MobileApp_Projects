package com.example.moviereviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MovieDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        var m = MovieClass("Venom", "When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego Venom to save his life", "English","03-10-2018", "Yes")
    }
}