package com.example.habitsapp

import android.content.Context

class HabitsModel(val context: Context) {

    val dbOpenHelper = DbOpenHelper(context)

    fun getHabits():List<Habit>{
        return dbOpenHelper.getAllHabits()
    }

    fun getHabits(type: HabitType):List<Habit>{
        //делать через запроос SQL
        return dbOpenHelper.getAllHabits()
            .filter { it.type==type }.toList()
    }

    fun save(habit: Habit){
        dbOpenHelper.addToDB(habit)
    }
}