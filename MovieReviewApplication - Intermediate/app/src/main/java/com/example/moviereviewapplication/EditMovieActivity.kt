package com.example.moviereviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviereviewapplication.MovieClass
import kotlinx.android.synthetic.main.activity_add_movie.*
import kotlinx.android.synthetic.main.activity_edit_movie.*
import android.widget.RadioButton
import android.view.View
import android.view.MenuItem
import android.view.Menu

class EditMovieActivity : AppCompatActivity() {
    lateinit var movie: MovieClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)

        val actionbar = supportActionBar
        actionbar!!.title = "MovieRater"
        actionbar.setDisplayHomeAsUpEnabled(true)

        chkUnsuitable1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ViolenceCheck1.visibility = View.VISIBLE
                LanguageCheck1.visibility = View.VISIBLE
            } else {
                ViolenceCheck1.visibility = View.GONE
                LanguageCheck1.visibility = View.GONE
            }
        }
        if (LanguageCheck1.isChecked and chkUnsuitable1.isChecked) {

        }
        if (ViolenceCheck1.isChecked and chkUnsuitable1.isChecked) {

        }
        val movie =
            MovieClass(
                name = "Venom",
                desc = "When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego Venom to save his life",
                language = "English",
                review = null,
                rating = null,
                releaseDate = "19-10-2018",
                suitable = "Yes",
                reason1 = false,
                reason2 = false,
            )

        NameInput1.setText(movie.name.toString())
        DescriptionInput1.setText(movie.desc.toString())
        when (movie.language) {
            "English" -> englishCheck1.isChecked = true
            "Chinese" -> chineseCheck1.isChecked = true
            "Malay" -> malayCheck1.isChecked = true
            "Tamil" -> indianCheck1.isChecked = true
        }
        ReleaseDateInput1.setText(movie.releaseDate.toString())
        chkUnsuitable1.isChecked = movie.suitable != "Yes"
        ViolenceCheck1.isChecked = movie.reason1.toString() == "true"
        LanguageCheck1.isChecked = movie.reason2.toString() == "true"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.editMovie -> {
                finish()
                true
            }
            else -> false
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}