package com.example.habitsapp

import Habit
import HabitType
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.util.TypedValue.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_habit.*
import kotlinx.android.synthetic.main.activity_habit_creation.*
import kotlinx.android.synthetic.main.activity_habit_creation.colorEdit
import kotlinx.android.synthetic.main.activity_habit_creation.descriptionEdit
import kotlinx.android.synthetic.main.activity_habit_creation.nameEdit
import kotlinx.android.synthetic.main.activity_habit_creation.periodicityEdit

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.habitsLayout

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //fillDataBase()

        if (intent.hasExtra("habitId")) {
            val id = intent.getStringExtra("habitId")
            val name = intent.getStringExtra("name")
            val description = intent.getStringExtra("description")
            val priority = intent.getStringExtra("priority")
            val type = intent.getStringExtra("type")
            val periodicity = intent.getStringExtra("periodicity")
            val color = intent.getStringExtra("color")
            val h = Habit(
                name,
                description,
                priority.toInt(),
                HabitType.valueOf(type),
                periodicity,
                color
            )
            h.id = id.toLong()
            setHabitToDataBase(h)
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

        dbWriter.delete(dbHelper.TABLE_NAME, "NAME=?", listOf<String>("n25").toTypedArray())
            //dbWriter.delete(dbHelper.TABLE_NAME, null, null)

        val cv = ContentValues()
        cv.put(dbHelper.NAME, "n25")
        cv.put(dbHelper.DESCRIPTION, "des11")
        cv.put(dbHelper.PRIORITY, "5")
        cv.put(dbHelper.TYPE, "Bad")
        cv.put(dbHelper.PERIODICITY, 2)
        cv.put(dbHelper.COLOR, "cyan")
        dbWriter.insert(dbHelper.TABLE_NAME, null, cv)
        dbWriter.close()
    }

    fun getHabitsFromDataBase(): List<Habit> {
        val dbHelper = DbOpenHelper(this)
        val dbReader = dbHelper.readableDatabase
        val cur = dbReader.query(
            dbHelper.TABLE_NAME,
            null, null, null, null, null, null
        )
//        var cur = dbReader.rawQuery("SELECT * FROM habits", null)
        val habs = mutableListOf<Habit>()

        while (cur.moveToNext()) {
            val name = cur.getString(1)
            val description = cur.getString(2)
            val priority = cur.getInt(3)
            val type = cur.getString(4)
            val periodicity = cur.getString(5)
            val color = cur.getString(6)
            val h = Habit(
                name,
                description,
                priority,
                HabitType.valueOf(type),
                periodicity,
                color
            )
            h.id = cur.getLong(0)
            habs.add(h)
        }
        dbReader.close()
        return habs
    }

    fun setHabitToDataBase(h: Habit) {
        val dbHelper = DbOpenHelper(this)
        val dbWriter = dbHelper.writableDatabase
        val cv = ContentValues()
        cv.put(dbHelper.NAME, h.name)
        cv.put(dbHelper.DESCRIPTION, h.descriptor)
        cv.put(dbHelper.PRIORITY, h.priority)
        cv.put(dbHelper.TYPE, h.type.toString())
        cv.put(dbHelper.PERIODICITY, h.periodicity)
        cv.put(dbHelper.COLOR, h.color)
        if (h.id != (-1).toLong()) {
            dbWriter.update(dbHelper.TABLE_NAME, cv, "_id = " + h.id.toString(), null)
        } else {
            h.id = dbWriter.insert(dbHelper.TABLE_NAME, null, cv)
        }
        dbWriter.close()
    }

    @SuppressLint("WrongConstant")
    fun getHabitView(habit: Habit): LinearLayout {
        val layout = LinearLayout(this)
        layout.setOrientation(LinearLayout.VERTICAL)
        val texts = listOf(
            habit.name,
            habit.descriptor,
            habit.priority.toString(),
            habit.type.toString(),
            habit.periodicity
        )

        texts.forEach {
            val tv = TextView(this)
            val color =
                try {Color.parseColor(habit.color)}
            catch(e:Exception){Color.parseColor("red")}
            tv.setTextColor(color)
            tv.text = (it + " ")
            tv.setTextSize(COMPLEX_UNIT_DIP, 30.toFloat())
            layout.addView(tv)
        }
        layout.setOnClickListener {
            Log.d(TAG, "ToHabitChange")
            val intent = Intent(this, HabitActivity::class.java)
            intent.putExtra("habitId", habit.id)
            intent.putExtra("name", habit.name)
            intent.putExtra("description", habit.descriptor)
            intent.putExtra("priority", habit.priority)
            intent.putExtra("type", habit.type.toString())
            intent.putExtra("periodicity", habit.periodicity)
            intent.putExtra("color", habit.color)
            startActivity(intent);
        }
        return layout
    }
}
