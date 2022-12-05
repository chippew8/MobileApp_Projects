package com.example.moviereviewapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDbAdapter(c: Context) {

    private val DATABASE_NAME = "movieData.db"
    private val DATABASE_TABLE = "myMovieDb"
    private val DATABASE_VERSION = 1
    private var _db: SQLiteDatabase? = null
    private val context: Context?= null

    val KEY_ID = "_id"
    val COLUMN_KEY_ID = 0
    val ENTRY_NAME = "name"
    val COLUMN_NAME_ID = 1
    val ENTRY_DESCRIPTION = "description"
    val COLUMN_DESC_ID = 2
    val ENTRY_LANGUAGE = "language"
    val COLUMN_LANG_ID = 3
    val ENTRY_DATE = "date"
    val COLUMN_DATE_ID = 4
    val ENTRY_SUITABLE = "suitable"
    val COLUMN_SUITABLE_ID = 5
    val ENTRY_REASON1 = "reason1"
    val COLUMN_REASON1_ID = 6
    val ENTRY_REASON2 = "reason2"
    val COLUMN_REASON2_ID = 7
    val ENTRY_RATING = "rating"
    val COLUMN_RATING_ID = 8
    val ENTRY_REVIEW = "review"
    val COLUMN_REVIEW_ID = 9

    protected val DATABASE_CREATE = ("create table " + DATABASE_TABLE + " "
                                    + "(" + KEY_ID + " integer primary key autoincrement, "
                                    + ENTRY_NAME + " Text, "
                                    + ENTRY_DESCRIPTION + " Text,"
                                    + ENTRY_LANGUAGE + " Text,"
                                    + ENTRY_DATE + " Text,"
                                    + ENTRY_SUITABLE + " Text, "
                                    + ENTRY_REASON1 + " Text, "
                                    + ENTRY_REASON2 + " Text, "
                                    + ENTRY_RATING + " Text, "
                                    + ENTRY_REVIEW + " Text);")


    private val MYDBADAPTER_LOG_CAT = "MY_LOG"

    private var dbHelper: MyDBOpenHelper? = null

    init {
        dbHelper = MyDBOpenHelper(c, DATABASE_NAME, DATABASE_VERSION)
    }

    fun close() {
        //TODO 2 : close the table using the SQLite database handler
        _db?.close()
    }


    fun open() {
        //TODO 3 : Open DB using the appropriate methods
        try{
            _db = dbHelper?.getWritableDatabase()
        }catch(e: SQLiteException){
            _db = dbHelper?.getReadableDatabase()
        }
    }

    fun insertEntry(entryName: String, entryDesc: String, entryLang: String, entryDate: String, entrySuit: String, entryR1: String, entryR2: String, entryRate: String, entryReview: String): Long? {
        //TODO 4 - insert record into table
        val newEntryValues = ContentValues()

        newEntryValues.put(ENTRY_NAME, entryName)
        newEntryValues.put(ENTRY_DESCRIPTION, entryDesc)
        newEntryValues.put(ENTRY_LANGUAGE, entryLang)
        newEntryValues.put(ENTRY_DATE, entryDate)
        newEntryValues.put(ENTRY_SUITABLE, entrySuit)
        newEntryValues.put(ENTRY_REASON1, entryR1)
        newEntryValues.put(ENTRY_REASON2, entryR2)
        newEntryValues.put(ENTRY_RATING, entryRate)
        newEntryValues.put(ENTRY_REVIEW, entryReview)

        return _db?.insert(DATABASE_TABLE, null, newEntryValues)
    }

    fun removeEntry(_rowIndex: Int): Boolean {
        //TODO 5 - remove record from table
        if (_db!!.delete(DATABASE_TABLE, KEY_ID + " = " + _rowIndex, null) <= 0){
            Log.w(MYDBADAPTER_LOG_CAT, "Removing entry where id = $_rowIndex failed")
            return false
        }
        return true
    }

    fun updateEntry(_rowIndex: Int, entryName: String, entryTel: String): Boolean {


        return false
    }

    fun retrieveAllEntriesCursor(): Cursor? {
        //TODO 6 - retrieve all records from table

        var c: Cursor? = null
        try{
            c = _db?.query(DATABASE_TABLE, arrayOf(KEY_ID, ENTRY_NAME, ENTRY_DESCRIPTION, ENTRY_DATE, ENTRY_SUITABLE, ENTRY_REASON1, ENTRY_REASON2, ENTRY_RATING, ENTRY_REVIEW),
            null,
            null,
            null,
            null,
            null)
        }catch (e: SQLiteException){
            Log.w(DATABASE_TABLE, "Retrieve fail")
        }
        return c
    }



    inner class MyDBOpenHelper(c: Context, db_name : String, ver_no : Int ): SQLiteOpenHelper(c, db_name, null, ver_no){


        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(DATABASE_CREATE)
            Log.w(MYDBADAPTER_LOG_CAT, "HELPER : DB $DATABASE_TABLE created!")

        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }
    }
}