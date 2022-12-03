package com.example.moviereviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        var m = MovieClass("Venom", "When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego Venom to save his life", "English","03-10-2018", "Yes")

        findViewById<TextView>(R.id.titleVal).text = m.name
        findViewById<TextView>(R.id.overviewVal).text = m.desc
        findViewById<TextView>(R.id.languageVal).text = m.language
        findViewById<TextView>(R.id.dateVal).text = m.releaseDate
        findViewById<TextView>(R.id.suitableVal).text = m.suitable
    }
}

