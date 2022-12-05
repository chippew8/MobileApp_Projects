package com.example.moviereviewapplication

import android.app.Application
import android.content.Context
import android.database.Cursor
import java.util.ArrayList

class MyMovies() : Application() {
    private var movieList: ArrayList<String> = ArrayList<String>()
    private var movieIdList: ArrayList<Int> = ArrayList<Int>()

    companion object {

        val ourInstance = MyMovies()
    }

//TODO 8: Make use of MyDBAdapter's method to perform
//  - insert,
//  - delete,
//  - retrieval of data.

    fun addToDatabase(entryName: String, entryDesc: String, entryLang: String, entryDate: String, entrySuit: String, entryR1: String, entryR2: String, entryRate: String, entryReview: String, c: Context): Long? {
        val db = MyDbAdapter(c)
        db.open()

        val rowIDofInsertedEntry = db.insertEntry(entryName, entryDesc, entryLang, entryDate, entrySuit, entryR1, entryR2, entryRate, entryReview)

        db.close()

        return rowIDofInsertedEntry

    }

    fun deleteFrmDatabase(rowID: Int, c: Context): Boolean {

        val db = MyDbAdapter(c)
        db.open()

        val id = movieIdList[rowID]

        val updateStatus = db.removeEntry(id)

        db.close()


        return updateStatus

    }


    fun retrieveAll(c: Context): List<String> {

        val myCursor: Cursor?
        var myString = ""
        val db = MyDbAdapter(c)
        db.open()
        movieIdList.clear()
        movieList.clear()
        myCursor = db.retrieveAllEntriesCursor()
        if (myCursor != null && myCursor!!.count > 0){
            do{
                movieIdList.add(myCursor.getInt(db.COLUMN_KEY_ID))
                myString = myCursor.getString(db.COLUMN_NAME_ID) + " - " + myCursor.getString(db.COLUMN_DESC_ID) + "\n"
                movieList.add(myString)
            }while(myCursor.moveToNext())
        }

        db.close()


        return movieList

    }



}