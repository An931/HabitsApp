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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fillDataBase()



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
        val habits = getHabitsFromDataBase()
        habits.forEach { habitsLayout.addView(getHabitView(it)) }

        fab.setOnClickListener {
            Log.d(TAG, "toCreateButton")
            val intent = Intent(this, HabitActivity::class.java)
            startActivity(intent);
        }
        Log.d(TAG, "onCreate")
    }

    fun fillDataBase() {
        val dbHelper = DbOpenHelper(this)
        val dbWriter = dbHelper.writableDatabase

//        dbWriter.delete(dbHelper.TABLE_NAME, "NAME=?", listOf<String>("n1").toTypedArray())
        dbWriter.delete(dbHelper.TABLE_NAME, null, null)

        val cv = ContentValues()
        cv.put(dbHelper.NAME, "n25")
        cv.put(dbHelper.DESCRIPTION, "des11")
        cv.put(dbHelper.PRIORITY, "5")
        cv.put(dbHelper.TYPE, "Bad")
        cv.put(dbHelper.PERIODICITY, 2)
        cv.put(dbHelper.COLOR, 89)
        dbWriter.insert(dbHelper.TABLE_NAME, null, cv)
        dbWriter.close()
    }

    fun getHabitsFromDataBase(): List<Habit> {
        val dbHelper = DbOpenHelper(this)
        val dbReader = dbHelper.readableDatabase
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
            val type = cur.getString(4)
            val periodicity = cur.getString(5)
            val color = cur.getInt(6)
            habs.add(
                Habit(
                    name,
                    description,
                    priority,
                    HabitType.valueOf(type),
                    periodicity,
                    color
                )
            )
        }
        dbReader.close()
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
