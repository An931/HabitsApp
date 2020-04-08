package com.example.habitsapp
//
//import android.content.ContentValues
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//
//
//private val DB_VERSION = 2
//private val DB_NAME = "test"
//
//class DbOpenHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
//    val TABLE_NAME = "habits"
//
//    //    val LOGIN = "login"
////    val PASSW = "passw"
//    //название, описание, приоритет, тип, периодичность и цвет
//    val NAME = "name"
//    val DESCRIPTION = "description"
//    val PRIORITY = "priority"
//    val TYPE = "type"
//    val PERIODICITY = "periodicity"
//    val COLOR = "color"
//
//    private val CREATE_TABLE =
//        ("create table " + TABLE_NAME + " ( _id integer primary key autoincrement, "
//                + NAME + " TEXT, "
//                + DESCRIPTION + " TEXT, "
//                + PRIORITY + " INTEGER, "
//                + TYPE + " TEXT, "
//                + PERIODICITY + " TEXT, "
//                + COLOR + " TEXT )")
//
//    private val DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME
//
//
//    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
//        //sqLiteDatabase.execSQL(DROP_TABLE)
//        sqLiteDatabase.execSQL(CREATE_TABLE)
//    }
//
//    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}
//
//    fun getAll(): List<Habit> {
//        val dbReader = readableDatabase
//        val cur = dbReader.query(
//            TABLE_NAME,
//            null, null, null, null, null, null
//        )
////        var cur = dbReader.rawQuery("SELECT * FROM habits", null)
//        val habs = mutableListOf<Habit>()
//
//        while (cur.moveToNext()) {
//            val name = cur.getString(1)
//            val description = cur.getString(2)
//            val priority = cur.getInt(3)
//            val type = cur.getString(4)
//            val periodicity = cur.getString(5)
//            val color = cur.getString(6)
//            val h = Habit(
//                name,
//                description,
//                priority,
//                type,
//                periodicity,
//                color
//            )
////            h.id = cur.getLong(0)
//            habs.add(h)
//        }
//        dbReader.close()
//        return habs
//    }
//
//    fun insert(h: Habit) {
//        val dbWriter = writableDatabase
//        val cv = ContentValues()
//        cv.put(NAME, h.name)
//        cv.put(DESCRIPTION, h.descriptor)
//        cv.put(PRIORITY, h.priority)
//        cv.put(TYPE, h.type.toString())
//        cv.put(PERIODICITY, h.periodicity)
//        cv.put(COLOR, h.color)
////        if (h.id != (-1).toLong()) {
////            dbWriter.update(TABLE_NAME, cv, "_id = " + h.id.toString(), null)
////        } else {
////            h.id = dbWriter.insert(TABLE_NAME, null, cv)
////        }
//        dbWriter.insert(TABLE_NAME, null, cv)
//        dbWriter.close()
//    }
//
//    fun cleanDb(){
//        writableDatabase.delete(TABLE_NAME, null, null)
//    }
//}
