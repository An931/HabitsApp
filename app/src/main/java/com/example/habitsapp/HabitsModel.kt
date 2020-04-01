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

//    fun getHabits(type: HabitType): List<Habit> {
//        //делать через запроос SQL
//        return dbOpenHelper.getAllHabits().filter { it.type == type }.toList()
//    }

    fun getHabits(
        type: HabitType,
        nameStr: String = "",
        sorting: SortingParameter = SortingParameter(SortBy.PRIORITY, SortOrder.ASCENDING)
    ): List<Habit> {

        //делать через запроос SQL

        var habits = dbOpenHelper.getAllHabits().filter {
            it.type == type && it.name.contains(nameStr)
        }
        when (sorting.sortBy) {
            SortBy.NAME -> habits = habits.sortedBy { it.name }
            SortBy.PRIORITY -> habits =
                habits.sortedWith(compareBy<Habit> { it.priority }.thenBy { it.name })
//            SortBy.TIME -> habits = habits.sortedBy { it.name }
        }
        if (sorting.order == SortOrder.DESCENDING)
            habits = habits.reversed()

        return habits
    }


    fun save(habit: Habit) {
        habitsChanged.postValue(true)
        dbOpenHelper.addToDB(habit)
    }
}

class SortingParameter(val sortBy: SortBy, val order: SortOrder)

enum class SortBy {
    NAME, PRIORITY
}

enum class SortOrder {
    ASCENDING,// возрастание
    DESCENDING//убывание
}