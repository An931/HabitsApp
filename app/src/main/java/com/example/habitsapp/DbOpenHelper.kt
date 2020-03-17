package com.example.habitsapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


private val DB_VERSION = 1
private val DB_NAME = "test"

class DbOpenHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    val TABLE_NAME = "habits"
    val LOGIN = "login"
    val PASSW = "passw"
    private val CREATE_TABLE =
        ("create table " + TABLE_NAME + " ( _id integer primary key autoincrement, "
                + LOGIN + " TEXT, " + PASSW + " TEXT)")


    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}

    companion object {


    }
}
//public class DbOpenHelper extends SQLiteOpenHelper{
//
//    private static final int DB_VERSION = 1;
//    private static final String DB_NAME = "test";
//
//    public static final String TABLE_NAME = "users";
//    public static final String LOGIN = "login";
//    public static final String PASSW = "passw";
//    private static final String CREATE_TABLE = "create table " + TABLE_NAME + " ( _id integer primary key autoincrement, "
//    + LOGIN + " TEXT, " + PASSW + " TEXT)";
//
//    public DbOpenHelper(Context context) {
//        super(context, DB_NAME, null,DB_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(CREATE_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//    }
//}