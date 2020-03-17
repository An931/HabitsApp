package com.example.habitsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_habit_creation.*


class HabitCreationActivity : AppCompatActivity() {
    val TAG = "HabitCreationActivity"
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_habit_creation)

        if(intent.hasExtra("habitId")){
            //set texts
        }

        saveHabitButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("habitId", 0) // !!!! не 0
            //create habit

            //название, описание, приоритет, тип, периодичность и цвет
            intent.putExtra("name", nameEdit.text)
            intent.putExtra("description", descriptionEdit.text)
            intent.putExtra("priority", priorityEdit.text)
            intent.putExtra("type", typeEdit.text)
            //intent.putExtra("periodicity", periodicityEdit.text)
            intent.putExtra("color", colorEdit.text)

            startActivity(intent);
            Log.d(TAG, "Save")
        }
        Log.d(TAG, "onCreate")
    }
}
