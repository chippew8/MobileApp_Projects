package com.example.moviereviewapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var myDB:MyDbAdapter? = null
    var moviesAdapter: ArrayAdapter<String>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerForContextMenu(moviesLV)

    }

    private fun toggleVisibility() {
        if (moviesLV.count > 0) {
            noitemsTV.visibility = View.GONE
            moviesLV.visibility = View.VISIBLE
        } else {
            moviesLV.visibility = View.GONE
            noitemsTV.visibility = View.VISIBLE
        }
    }

    private fun retrieveMovies(){
        val movieList: List<String>
        val mc = MyMovies.ourInstance
        movieList = mc.retrieveAll(applicationContext)
        moviesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, movieList)
        moviesLV.adapter = moviesAdapter
    }

    override fun onResume() {
        retrieveMovies()
        toggleVisibility()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.addMovie){
            var MainPage = Intent(this, AddMovie::class.java)
            startActivity(MainPage)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View,
                                     menuInfo: ContextMenu.ContextMenuInfo) {

        if (v.id == R.id.moviesLV) {
            menu.add(1, 0, 0, "Remove")
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val mc = MyMovies.ourInstance
        mc.deleteFrmDatabase(info.position, applicationContext)
        retrieveMovies()
        toggleVisibility()
        return super.onContextItemSelected(item)
    }


}