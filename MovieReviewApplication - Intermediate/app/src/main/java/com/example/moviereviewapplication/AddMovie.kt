package com.example.moviereviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_movie.*
import kotlinx.android.synthetic.main.activity_movie_detail.*


class AddMovie : AppCompatActivity() {
    var movieName : String = ""
    var movieDesc : String = ""
    var movieLang : String = "English"
    var movieRelease : String = ""
    var suitable : String = ""
    var reason1 : Boolean = false
    var reason2 : Boolean = false


    fun ErrorValidation(): Boolean {
        var count : Int = 0
        if (NameInput.text.toString().isEmpty() == true){
            NameInput.error = "Name cannot be empty"

        }else{
            count += 1
            movieName = NameInput.text.toString()

        }
        if (DescriptionInput.text.toString().isEmpty() == true){
            DescriptionInput.error = "Description cannot be empty"
        }else{
            count += 1
            movieDesc = DescriptionInput.text.toString()

        }
        if (ReleaseDateInput.text.toString().isEmpty() == true){
            ReleaseDateInput.error = "Release Date cannot be empty"
        }else{
            count += 1
            movieRelease = ReleaseDateInput.text.toString()

        }

        return (count == 3)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        languagePick.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                if (p1 == R.id.englishCheck) {
                    movieLang = "English"
                } else if (p1 == R.id.chineseCheck) {
                    movieLang = "Chinese"
                } else if (p1 == R.id.malayCheck) {
                    movieLang = "Malay"
                } else if (p1 == R.id.indianCheck) {
                    movieLang = "Indian"
                }

            }
        })
        chkUnsuitable.setOnClickListener {
            if (chkUnsuitable.isChecked == true) {
                ViolenceCheck.setVisibility(View.VISIBLE)
                LanguageCheck.setVisibility(View.VISIBLE)
            }else{
                ViolenceCheck.setVisibility(View.GONE)
                LanguageCheck.setVisibility(View.GONE)
            }
        }

        ViolenceCheck.setOnClickListener {
            reason1 = ViolenceCheck.isChecked
        }
        LanguageCheck.setOnClickListener {
            reason2 = LanguageCheck.isChecked
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == R.id.addMovie){
            if (ErrorValidation()) {
                suitable = if (reason1 && reason2) {
                    "No (Language & Violence)"
                }else if(reason1 && !reason2){
                    "No (Violence)"
                }else if(reason2 && !reason1){
                    "No (Language)"
                }else{
                    "Yes"
                }
                var m = MovieClass(movieName, movieDesc, movieLang, movieRelease, suitable, reason1, reason2, null, null)
                val movieIntent = Intent(this, MovieDetail::class.java)
                movieIntent.putExtra("movie", m)
                startActivity(movieIntent)
            }
        }else if(item?.itemId == R.id.clearEntry){
            NameInput.setText(" ")
            DescriptionInput.setText(" ")
            ReleaseDateInput.setText(" ")
            ViolenceCheck.setChecked(false)
            LanguageCheck.setChecked(false)
            chkUnsuitable.setChecked(false)
            ViolenceCheck.setVisibility(View.GONE)
            LanguageCheck.setVisibility(View.GONE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}