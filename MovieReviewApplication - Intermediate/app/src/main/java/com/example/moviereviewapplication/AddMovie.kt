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
    var movieName: String = ""
    var movieDesc: String = ""
    var movieLang: String = "English"
    var movieRelease: String = ""
    var notsuitable: String = ""
    var violence: String = ""
    var language: String = ""

    fun nameCheck(): Boolean {
        if (NameInput.text.toString().isEmpty() == true){
            NameInput.setError("Name cannot be empty")
            return false
        }else{
            movieName = NameInput.text.toString()
            return true
        }
    }
    fun descCheck(): Boolean {
        if (DescriptionInput.text.toString().isEmpty() == true){
            DescriptionInput.setError("Description cannot be empty")
            return false
        }else{
            movieDesc = DescriptionInput.text.toString()
            return true
        }
    }
    fun dateCheck(): Boolean {
        if (ReleaseDateInput.text.toString().isEmpty() == true){
            ReleaseDateInput.setError("Release date cannot be empty")
            return false
        }else{
            movieRelease = ReleaseDateInput.text.toString()
            return true
        }
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

        if(chkUnsuitable.isChecked == true){
            ViolenceCheck.setVisibility(View.VISIBLE)
            LanguageCheck.setVisibility(View.VISIBLE)
        }else{
            ViolenceCheck.setVisibility(View.GONE)
            LanguageCheck.setVisibility(View.GONE)
        }

//        chkUnsuitable.setOnClickListener{
//            if (chkUnsuitable.isChecked == true) {
//
//                ViolenceCheck.setVisibility(View.VISIBLE)
//                LanguageCheck.setVisibility(View.VISIBLE)
//                notsuitable = "No"
//                if (notsuitable == "No"){
//                    if (LanguageCheck.isChecked == true){
//                        BadLanguage = true
//                    }else {
//                        BadLanguage = false
//                    }
//                    if (ViolenceCheck.isChecked == true){
//                        BadViolence = true
//                    }else{
//                        BadViolence = false
//                    }
//
//                    if (LanguageCheck == true && BadViolence == true){
//                        notsuitable = "No (Language & Violence)"
//                    }else if (BadLanguage == true && BadViolence == false){
//                        notsuitable = "No (Language)"
//                    }else if (BadViolence == true && BadLanguage == false){
//                        notsuitable = "No (Violence)"
//                    }
//                }
//            }else if (chkUnsuitable.isChecked == false) {
//                ViolenceCheck.setVisibility(View.GONE)
//                LanguageCheck.setVisibility(View.GONE)
//                notsuitable = "Yes"
//            }
//        }




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == R.id.addMovie){
            if (nameCheck() && descCheck() && dateCheck()) {
                var movieIntent = Intent(this, MovieDetail::class.java)
                movieIntent.putExtra("titleVal", movieName)
                movieIntent.putExtra("overviewVal", movieDesc)
                movieIntent.putExtra("languageVal", movieLang)
                movieIntent.putExtra("dateVal", movieRelease)
                movieIntent.putExtra("suitableVal", notsuitable)
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

    fun displayToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}