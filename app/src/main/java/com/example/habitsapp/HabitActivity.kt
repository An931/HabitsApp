package com.example.habitsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_habit_creation.*


class HabitActivity : AppCompatActivity() {
    val TAG = "HabitActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit)

//        if(intent.hasExtra("habitId")){
//            //set texts
//        }

        saveHabitButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("habitId", 0) // !!!! не 0
            //create habit

            //название, описание, приоритет, тип, периодичность и цвет
//            Log.d(TAG, nameEdit.text.toString())
            intent.putExtra("name", nameEdit.text.toString())
            intent.putExtra("description", descriptionEdit.text.toString())
            intent.putExtra("priority", priorityEdit.text.toString())
            intent.putExtra("type", typeEdit.text.toString())
            //intent.putExtra("periodicity", periodicityEdit.text)
            intent.putExtra("color", colorEdit.text.toString())

            startActivity(intent);
            Log.d(TAG, "Save")
        }
        Log.d(TAG, "onCreate")
    }
}
