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

        if (intent.hasExtra("habitId")) {
            nameEdit.setText(intent.getStringExtra("name"))
            descriptionEdit.setText(intent.getStringExtra("description"))

            val spinnerItem = intent.getIntExtra("priority", 1) - 1
            prioritySpinner.setSelection(spinnerItem)

            when (intent.getStringExtra("type")) {
                "Good" -> radio_good.isChecked = true
                "Neutral" -> radio_neutral.isChecked = true
                "Bad" -> radio_bad.isChecked = true
            }
            periodicityEdit.setText(intent.getStringExtra("periodicity"))
            colorEdit.setText(intent.getStringExtra("color"))
            habitId.setText(intent.getLongExtra("habitId", -1).toString())
        }

        saveHabitButton.setOnClickListener {
            if (!(nameEdit.text.isEmpty() || descriptionEdit.text.isEmpty()
                        || periodicityEdit.text.isEmpty() || colorEdit.text.isEmpty())
            ) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("habitId", habitId.text)


                //название, описание, приоритет, тип, периодичность и цвет
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

        }
        Log.d(TAG, "onCreate")
    }
}
