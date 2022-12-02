package com.example.moviereviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.content.Intent

import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        registerForContextMenu(reviewVal)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var intent = intent
        var titleVal = intent.getStringExtra("titleVal")
        var overviewVal = intent.getStringExtra("overviewVal")
        var languageVal = intent.getStringExtra("languageVal")
        var dateVal = intent.getStringExtra("dateVal")
        var violence = intent.getStringExtra("violence")
        var language = intent.getStringExtra("language")
        var suitability : String = ""

        if (violence == "True" && language == "True") {
            suitability = "No (Language & Violence)"
        }else if(violence == "True" && language != "True"){
            suitability = "No(Violence)"
        }else if(language == "True" && violence != "True"){
            suitability = "No(Language)"
        }else{
            suitability = "Yes"
        }
        var m = MovieClass(titleVal.toString(), overviewVal.toString(), languageVal.toString(),dateVal.toString(), suitability,null)

        findViewById<TextView>(R.id.titleVal).text = m.name
        findViewById<TextView>(R.id.overviewVal).text = m.desc
        findViewById<TextView>(R.id.languageVal).text = m.language
        findViewById<TextView>(R.id.dateVal).text = m.releaseDate
        findViewById<TextView>(R.id.suitableVal).text = m.suitable

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

        }
        return super.onContextItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


