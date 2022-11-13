package com.example.moviereviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var suitableCategory: View
    var movieName: String = ""
    var movieDesc: String = ""
    var movieLang: String = ""
    var movieRelease: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        suitableCategory.visibility = View.GONE

        languagePick.setOnCheckedChangeListener(object:RadioGroup.OnCheckedChangeListener{

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


//        chkUnsuitable.setOnClickListener {
//            if(chkUnsuitable.isChecked == true)
//        }

        btnSubmit.setOnClickListener {

            movieName = NameInput.text.toString()
            movieDesc = DescriptionInput.text.toString()
            movieRelease = ReleaseDateInput.text.toString()


            displayToast("Title = " + movieName + " \n Overview = " + movieDesc + "\n Release Date = " +
            movieRelease + "\n Language = " + movieLang)
        }
    }

    fun displayToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}