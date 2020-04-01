package com.example.habitsapp

import android.content.Context
import androidx.lifecycle.MutableLiveData

class HabitsModel(val context: Context) {

    //make singleton

    val dbOpenHelper = DbOpenHelper(context)

    val habitsChanged = MutableLiveData<Boolean?>()

    fun getHabits(): List<Habit> {
        return dbOpenHelper.getAllHabits()
    }

    fun getHabits(type: HabitType): List<Habit> {
        //делать через запроос SQL
        return dbOpenHelper.getAllHabits().filter { it.type == type }.toList()
    }

    fun save(habit: Habit) {
        habitsChanged.postValue(true)
        dbOpenHelper.addToDB(habit)
    }
}