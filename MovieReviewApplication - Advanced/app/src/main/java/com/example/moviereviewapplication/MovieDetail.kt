package com.example.moviereviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.content.Intent
import android.graphics.Movie

import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        registerForContextMenu(reviewVal)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var intent = intent
        var movie = intent.getSerializableExtra("movie") as? MovieClass
        var rating = intent.getStringExtra("starRating")
        var review = intent.getStringExtra("reviewInput")


        movie?.review = review

        if (rating != null){
            movie?.rating = rating?.toFloat()
            ratingVal.setVisibility(View.VISIBLE)
            ratingVal.rating = movie?.rating.toString().toFloat()
            ratingVal.setEnabled(false)
        }
        if (review != null){
            reviewVal.text = review
        }

        findViewById<TextView>(R.id.titleVal).text = movie?.name.toString()
        findViewById<TextView>(R.id.overviewVal).text = movie?.desc.toString()
        findViewById<TextView>(R.id.languageVal).text = movie?.language.toString()
        findViewById<TextView>(R.id.dateVal).text = movie?.releaseDate.toString()
        findViewById<TextView>(R.id.suitableVal).text = movie?.suitable.toString()



    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v?.id == R.id.reviewVal){
            menu?.add(1, 1001, 1, "Add review")
        }

    }

    override fun onContextItemSelected(item: MenuItem):Boolean{

        if (item.itemId == 1001){
            var movieIntent = Intent(this, RatingActivity::class.java)
            val movie = intent.getSerializableExtra("movie") as? MovieClass
            movieIntent.putExtra("movie", movie)
            startActivity(movieIntent)
        }
        return super.onContextItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


