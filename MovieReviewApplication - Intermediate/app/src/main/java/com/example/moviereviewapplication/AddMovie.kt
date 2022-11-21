package com.example.moviereviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_movie.*

class AddMovie : AppCompatActivity() {
    var movieName: String = ""
    var movieDesc: String = ""
    var movieLang: String = ""
    var movieRelease: String = ""
    var notsuitable: String = "\nNot suitable for all ages = "
    var reason: String = "\nReason: "

    fun nameCheck(): Boolean {
        if (NameInput.text.toString().isEmpty() == true){
            NameInput.setError("Name cannot be empty")
            return false
        }else{
            movieName = "Title: " + NameInput.text.toString()
            return true
        }
    }
    fun descCheck(): Boolean {
        if (DescriptionInput.text.toString().isEmpty() == true){
            DescriptionInput.setError("Description cannot be empty")
            return false
        }else{
            movieDesc = "\nOverview: " +DescriptionInput.text.toString()
            return true
        }
    }
    fun dateCheck(): Boolean {
        if (ReleaseDateInput.text.toString().isEmpty() == true){
            ReleaseDateInput.setError("Release date cannot be empty")
            return false
        }else{
            movieRelease ="\nRelease Date: " + ReleaseDateInput.text.toString()
            return true
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)
        languagePick.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {

            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                if (p1 == R.id.englishCheck) {
                    movieLang = "\nLanguage: English"
                } else if (p1 == R.id.chineseCheck) {
                    movieLang = "\nLanguage: Chinese"
                } else if (p1 == R.id.malayCheck) {
                    movieLang = "\nLanguage: Malay"
                } else if (p1 == R.id.indianCheck) {
                    movieLang = "\nLanguage: Indian"
                }

            }
        })


        chkUnsuitable.setOnClickListener {
            if (chkUnsuitable.isChecked == true) {
                ViolenceCheck.setVisibility(View.VISIBLE)
                LanguageCheck.setVisibility(View.VISIBLE)
                notsuitable += "true"

                LanguageCheck.setOnClickListener {
                    if (LanguageCheck.isChecked == true) {
                        reason += "\n Language"
                    }
                }
                ViolenceCheck.setOnClickListener {
                    if (ViolenceCheck.isChecked == true) {
                        reason += "\n Violence"
                    }
                }
            } else if (chkUnsuitable.isChecked == false) {
                ViolenceCheck.setVisibility(View.GONE)
                LanguageCheck.setVisibility(View.GONE)
                notsuitable += "false"
            }
        }



        btnSubmit.setOnClickListener {
            if (nameCheck() && descCheck() && dateCheck()) {
                var btnFeedback: String = ""
                btnFeedback = movieName + movieDesc + movieRelease + movieLang + notsuitable + reason
                displayToast(btnFeedback)
            }
        }
    }

    fun displayToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}