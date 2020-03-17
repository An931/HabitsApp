package com.example.habitsapp

import Habit
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
    val habits = mutableListOf<Habit>(
        Habit("first", "des", 1, HabitType.Good, Color.CYAN),
        Habit("sec", "de", 7, HabitType.Neutral, Color.RED),
        Habit("thi", "desc", 3, HabitType.Good, Color.CYAN)
    )
//    val habits = mutableMapOf<Int, Habit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(intent.hasExtra("habitId")){
            val name = intent.getStringExtra("name")
            val description = intent.getStringExtra("description")
            val priority = intent.getStringExtra("priority").toInt()
            val type = intent.getStringExtra("type")
            //val periodicity = intent.getStringExtra("periodicity")
            val color = intent.getStringExtra("color")
            //color and type (?????)
            habits.add(Habit(name, description, priority, HabitType.Bad, Color.parseColor(color)))
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

    fun getHabitView(habit: Habit): LinearLayout {
        val layout = LinearLayout(this)
        val texts = listOf(habit.name, habit.descriptor, habit.type.toString())
        texts.forEach{
            val tv = TextView(this)
            tv.text = it
            layout.addView(tv)
        }
        layout.setOnClickListener{
            Log.d(TAG, "ToHabitChange")
            layout.setBackgroundColor(Color.parseColor("#33b5e5"))
            val intent = Intent(this, HabitCreationActivity::class.java)
            intent.putExtra("habitId", layout.id)
            startActivity(intent);
        }
        return layout
    }
}
