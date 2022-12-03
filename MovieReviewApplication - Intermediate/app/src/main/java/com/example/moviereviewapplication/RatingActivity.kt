package com.example.moviereviewapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_rating.*

class RatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        var intent = intent
        var titleVal = intent.getStringExtra("titleVal")
        var movieName : String = titleVal.toString()
        findViewById<TextView>(R.id.movieRate).text = "Enter your review for the movie: " + movieName
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.rate,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var rating : Float = movieRateIn.rating.toFloat()
        if (item.itemId == R.id.addMovie){
            var Rating = Intent(this, MovieDetail::class.java)
            Rating.putExtra("rating", rating)
            startActivity(Rating)
        }
        return super.onOptionsItemSelected(item)
    }
}