package com.example.moviereviewapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_rating.*

class RatingActivity : AppCompatActivity() {
    var StarRating : String = ""
    var reviewInput : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var intent = intent
        var movie = intent.getSerializableExtra("movie") as? MovieClass


        findViewById<TextView>(R.id.movieRate).text = "Enter your review for the movie: " + movie?.name.toString()

        submit.setOnClickListener{
            var StarRating = movieRateIn.rating.toString()
            var reviewInput = movieViewIn.text.toString()
            val ratingIntent = Intent(this, MovieDetail::class.java)
            ratingIntent.putExtra("movie", movie)
            ratingIntent.putExtra("starRating", StarRating)
            ratingIntent.putExtra("reviewInput", reviewInput)
            startActivity(ratingIntent)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }
}