package com.example.habitsapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView

class Habit(
    var name: String,
    var descriptor: String,
    var priority: Int,
    var type: HabitType,
    var periodicity: String,
    var color: String
){
    var id :Long = -1

    fun getView(context: Context): LinearLayout {
        val layout = LinearLayout(context)
        layout.setOrientation(LinearLayout.VERTICAL)
        val texts = listOf(
            name,
            descriptor,
            priority.toString(),
            type.toString(),
            periodicity
        )

        texts.forEach {
            val tv = TextView(context)
            val color =
                try {
                    Color.parseColor(color)}
                catch(e:Exception){
                    Color.parseColor("red")}
            tv.setTextColor(color)
            tv.text = (it + " ")
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30.toFloat())
            layout.addView(tv)
        }
//        layout.setOnClickListener {
//            val intent = Intent(this, HabitActivity::class.java)
//            intent.putExtra("habitId", habit.id)
//            intent.putExtra("name", habit.name)
//            intent.putExtra("description", habit.descriptor)
//            intent.putExtra("priority", habit.priority)
//            intent.putExtra("type", habit.type.toString())
//            intent.putExtra("periodicity", habit.periodicity)
//            intent.putExtra("color", habit.color)
//            startActivity(intent);
//        }
        return layout
    }
}

//название, описание, приоритет, тип, периодичность, цвет

enum class HabitType {
    Good,
    Bad,
    Neutral,
}

//enum class Color(val rgb: String) {
//    Red("f00"),
//    Green("000"),
//    Blue("000")
//}

enum class Periodicity {
    Hour, Day, Week, Month, Year
} //change to times-per-smth