//package com.example.moviereviewapplication
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.ContextMenu
//import android.view.Menu
//import android.view.MenuItem
//import android.view.View
//import android.widget.TextView
//
//import kotlinx.android.synthetic.main.activity_movie_detail.*
//
//
//class MovieDetail : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_movie_detail)
//        registerForContextMenu(Review)
//        var intent = intent
//        var titleVal = intent.getStringExtra("titleVal")
//        var overviewVal = intent.getStringExtra("overviewVal")
//        var languageVal = intent.getStringExtra("languageVal")
//        var dateVal = intent.getStringExtra("dateVal")
//        var suitableVal = intent.getStringExtra("suitableVal")
//
//        var m = MovieClass(titleVal.toString(), overviewVal.toString(), languageVal.toString(),dateVal.toString(), suitableVal.toString())
//
//        findViewById<TextView>(R.id.titleVal).text = m.name
//        findViewById<TextView>(R.id.overviewVal).text = m.desc
//        findViewById<TextView>(R.id.languageVal).text = m.language
//        findViewById<TextView>(R.id.dateVal).text = m.releaseDate
//        findViewById<TextView>(R.id.suitableVal).text = m.suitable
//
//
//    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.details,menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onCreateContextMenu(
//        menu: ContextMenu?,
//        v: View?,
//        menuInfo: ContextMenu.ContextMenuInfo?
//    ){
//        super.onCreateContextMenu(menu, v, menuInfo)
//        if (v?.id == R.id.reviewVal){
//            menu?.add(1, 1001, 1, "Add review")
//        }
//
//    }
//
//    override fun onContextItemSelected(item: MenuItem):Boolean{
//        if (item?.itemId == 1001){
//            finish()
//        }
//        return super.onContextItemSelected(item)
//    }
//}

package com.example.moviereviewapplication

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.moviereviewapplication.MovieClass
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        var title = intent.getStringExtra("titleVal")
        var description = intent.getStringExtra("overviewVal")
        var date = intent.getStringExtra("dateVal")
        var language = intent.getStringExtra("languageVal")
        var suitable1 = intent.getStringExtra("suitable1")
        var suitable2 = intent.getStringExtra("suitable2")
//        var star = intent.getFloatExtra("star", -1f)
//        var review = intent.getStringExtra("review")


//        if (title == null){
//            title = titleinput.text.toString()
//            description = overviewinput.text.toString()
//            language = languageinput.text.toString()
//
//        }
        var m1 = MovieClass(
            title.toString(),
            description.toString(),
            language.toString(),
            date.toString(),
            suitable1.toString(),

        )
        var suitables = m1.suitable
        registerForContextMenu(findViewById<TextView>(R.id.reviewinput))
        if (m1.suitable2 == "both") {
            suitable2 = " (Language, Violence)"
        } else if (m1.suitable2 == "none") {
            suitable2 = " (None selected)"
        } else if (m1.suitable2 == "violence") {
            suitable2 = " (Violence)"
        } else if (m1.suitable2 == "language") {
            suitable2 = " (Language)"
        }
        suitables += suitable2
        if (m1.stars < 0){
            ratedbar.setVisibility(View.GONE)
        }
        else if (m1.stars >= 0){

            ratedbar.setVisibility(View.VISIBLE)
            ratedbar.rating = m1.stars
            reviewinput.text = m1.review
        }

        findViewById<TextView>(R.id.titleinput).text = m1.title
        findViewById<TextView>(R.id.overviewinput).text = m1.overview
        findViewById<TextView>(R.id.languageinput).text = m1.language
        findViewById<TextView>(R.id.dateinput).text = m1.date
        findViewById<TextView>(R.id.suitableinput).text = suitables

    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v?.id == R.id.reviewinput){
            menu?.add(1, 1001, 1, "Add Review")
        }
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        var title = intent.getStringExtra("title")
        var description = intent.getStringExtra("description")
        var date = intent.getStringExtra("release")
        var language = intent.getStringExtra("language")
        var suitable1 = intent.getStringExtra("suitable1")
        var suitable2 = intent.getStringExtra("suitable2")
        var star = intent.getFloatExtra("star", 0f)
        var review = intent.getStringExtra("review")
        if (item?.itemId == 1001){
            var intent = Intent(this, RatingActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("description", description)
            intent.putExtra("release", date)
            intent.putExtra("language", language)
            intent.putExtra("suitable1", suitable1)
            intent.putExtra("suitable2", suitable2)
            intent.putExtra("star", star)
            intent.putExtra("review", review)
            startActivity(intent)

        }
        return super.onContextItemSelected(item)
    }


}

