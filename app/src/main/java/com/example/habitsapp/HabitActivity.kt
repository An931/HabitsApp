package com.example.habitsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_habit.*
import kotlinx.android.synthetic.main.activity_habit_creation.*
import kotlinx.android.synthetic.main.activity_habit_creation.colorEdit
import kotlinx.android.synthetic.main.activity_habit_creation.descriptionEdit
import kotlinx.android.synthetic.main.activity_habit_creation.nameEdit
import kotlinx.android.synthetic.main.activity_habit_creation.periodicityEdit
import kotlinx.android.synthetic.main.activity_habit_creation.saveHabitButton
import kotlinx.android.synthetic.main.activity_habit_creation.typeEdit


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
            intent.putExtra("priority", prioritySpinner.selectedItem.toString())
            val type = findViewById<RadioButton>(typeRadioGroup.checkedRadioButtonId)
            intent.putExtra("type", type.text)
            intent.putExtra("periodicity", periodicityEdit.text.toString())
            intent.putExtra("color", colorEdit.text.toString())

            startActivity(intent);
            Log.d(TAG, "Save")
        }
        Log.d(TAG, "onCreate")
    }
}
