package com.example.habitsapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


private val DB_VERSION = 2
private val DB_NAME = "test"

class DbOpenHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    val TABLE_NAME = "habits"

    //    val LOGIN = "login"
//    val PASSW = "passw"
    //название, описание, приоритет, тип, периодичность и цвет
    val NAME = "name"
    val DESCRIPTION = "description"
    val PRIORITY = "priority"
    val TYPE = "type"
    val PERIODICITY = "periodicity"
    val COLOR = "color"

    private val CREATE_TABLE =
        ("create table " + TABLE_NAME + " ( _id integer primary key autoincrement, "
                + NAME + " TEXT, "
                + DESCRIPTION + " TEXT, "
                + PRIORITY + " INTEGER, "
                + TYPE + " TEXT, "
                + PERIODICITY + " TEXT, "
                + COLOR + " TEXT )")

    private val DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME


    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        //sqLiteDatabase.execSQL(DROP_TABLE)
        sqLiteDatabase.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}

}
