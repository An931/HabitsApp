package com.example.habitsapp

import Habit
import HabitType
import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
//    val habits = mutableListOf<Habit>(
//        Habit("first", "des", 1, HabitType.Good, Color.CYAN),
//        Habit("sec", "de", 7, HabitType.Neutral, Color.RED),
//        Habit("thi", "desc", 3, HabitType.Good, Color.CYAN)
//    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //fillDataBase()

        val habits = getHabitsFromDataBase()

        if (intent.hasExtra("habitId")) {
            val name = intent.getStringExtra("name")
            val description = intent.getStringExtra("description")
            val priority = intent.getStringExtra("priority")
            val type = intent.getStringExtra("type")
            val periodicity = intent.getStringExtra("periodicity")
            val color = intent.getStringExtra("color")
            //color and type (?????)
            setNewHabitToDataBase(
                Habit(
                    name, description, priority.toInt(), HabitType.valueOf(type),
                    periodicity, Color.parseColor(color)
                )
            )
        }
        habits.forEach { habitsLayout.addView(getHabitView(it)) }

        fab.setOnClickListener {
            Log.d(TAG, "toCreateButton")
            val intent = Intent(this, HabitActivity::class.java)
            //intent.putExtra("num", toSquareButton.text.toString().toInt())
            startActivity(intent);
        }
        Log.d(TAG, "onCreate")
    }

    fun fillDataBase() {
        val dbHelper = DbOpenHelper(this)
        val dbWriter = dbHelper.writableDatabase

        dbWriter.delete(dbHelper.TABLE_NAME, "NAME=?", listOf<String>("n1").toTypedArray())

        val cv = ContentValues()
        cv.put(dbHelper.NAME, "n1")
        cv.put(dbHelper.DESCRIPTION, "des11")
        cv.put(dbHelper.PRIORITY, "5")
        cv.put(dbHelper.TYPE, "Bad")
        cv.put(dbHelper.PERIODICITY, 2)
        cv.put(dbHelper.COLOR, 89)
        dbWriter.insert(dbHelper.TABLE_NAME, null, cv)
        dbWriter.close()

//    cv.put(DbOpenHelper.LOGIN,loginEditText.getText().toString());
//    cv.put(DbOpenHelper.PASSW,passEditText.getText().toString());
//    db.insert(DbOpenHelper.TABLE_NAME,null,cv);
//    db.close();
    }

    fun getHabitsFromDataBase(): List<Habit> {
        val dbHelper = DbOpenHelper(this)
        val dbReader = dbHelper.readableDatabase
//        val cur = dbReader.query(
//            dbHelper.TABLE_NAME,
//            "NAME DESCRIPTION PRIORITY TYPE PERIODICITY COLOR".split(" ").toTypedArray(),
//            null, null, null, null, null
//        )
        val cur = dbReader.query(
            dbHelper.TABLE_NAME,
            null,
            null, null, null, null, null
        )
//        var cur = dbReader.rawQuery("SELECT * FROM habits", null)
        val habs = mutableListOf<Habit>()

        while (cur.moveToNext()) {
            val name = cur.getString(1)
            val description = cur.getString(2)
            val priority = cur.getInt(3)
            val priority2 = cur.getString(3)
            val type = cur.getString(4)
            val type2 = cur.getInt(4)
            val periodicity2 = cur.getInt(5)
            val periodicity = cur.getString(5)
            val color = cur.getInt(6)
//            val name = getString(cur.getColumnIndexOrThrow(dbHelper.NAME))
//            val description = cur.getString(cur.getColumnIndexOrThrow(dbHelper.DESCRIPTION))
//            val priority = cur.getInt(cur.getColumnIndexOrThrow(dbHelper.PRIORITY))
//            val priority2 = cur.getString(cur.getColumnIndexOrThrow(dbHelper.PRIORITY))
//            val type = cur.getString(cur.getColumnIndexOrThrow(dbHelper.TYPE))
//            val type2 = cur.getInt(cur.getColumnIndexOrThrow(dbHelper.TYPE))
//            val periodicity2 = cur.getInt(cur.getColumnIndexOrThrow(dbHelper.PERIODICITY))
//            val periodicity = cur.getString(cur.getColumnIndexOrThrow(dbHelper.PERIODICITY))
//            val color = cur.getInt(cur.getColumnIndexOrThrow(dbHelper.COLOR))
            habs.add(
                Habit(
                    name,
                    description,
                    priority,
                    HabitType.valueOf(type), //!!!!!!!!!!
                    periodicity,
                    color
                )
            )
        }
//        with(cur) {
//            while (moveToNext()) {
//                val name = getString(getColumnIndexOrThrow(dbHelper.NAME))
//                val description = cur.getString(getColumnIndexOrThrow(dbHelper.DESCRIPTION))
//                val priority = cur.getInt(getColumnIndexOrThrow(dbHelper.PRIORITY))
//                val priority2 = cur.getString(getColumnIndexOrThrow(dbHelper.PRIORITY))
//                val type = cur.getString(getColumnIndexOrThrow(dbHelper.TYPE))
//                val type2 = cur.getInt(getColumnIndexOrThrow(dbHelper.TYPE))
//                val periodicity2 = cur.getInt(getColumnIndexOrThrow(dbHelper.PERIODICITY))
//                val periodicity = cur.getString(getColumnIndexOrThrow(dbHelper.PERIODICITY))
//                val color = cur.getInt(getColumnIndexOrThrow(dbHelper.COLOR))
//                habs.add(
//                    Habit(
//                        name,
//                        description,
//                        priority,
//                        HabitType.valueOf(type),
//                        periodicity,
//                        color
//                    )
//                )
//            }
//        }

        return habs
    }

    fun setNewHabitToDataBase(h: Habit) {
        val dbHelper = DbOpenHelper(this)
        val dbWriter = dbHelper.writableDatabase
        val cv = ContentValues()
        cv.put(dbHelper.NAME, h.name)
        cv.put(dbHelper.DESCRIPTION, h.descriptor)
        cv.put(dbHelper.PRIORITY, h.priority)
        cv.put(dbHelper.TYPE, h.type.toString())
        cv.put(dbHelper.PERIODICITY, h.periodicity)
        cv.put(dbHelper.COLOR, h.color)
        dbWriter.insert(dbHelper.TABLE_NAME, null, cv)
        dbWriter.close()
    }

    fun getHabitView(habit: Habit): LinearLayout {
        val layout = LinearLayout(this)
        val texts = listOf(habit.name, habit.descriptor, habit.type.toString())
        texts.forEach {
            val tv = TextView(this)
            tv.text = it
            layout.addView(tv)
        }
        layout.setOnClickListener {
            Log.d(TAG, "ToHabitChange")
            layout.setBackgroundColor(Color.parseColor("#33b5e5"))
            val intent = Intent(this, HabitCreationActivity::class.java)
            intent.putExtra("habitId", layout.id)
            startActivity(intent);
        }
        return layout
    }
}
