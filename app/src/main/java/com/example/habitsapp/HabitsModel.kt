package com.example.habitsapp

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Room

class HabitsModel(val habitDB:HabitDao) {


    val changedHabits = MutableLiveData<List<Habit>?>()

    fun getHabits(): List<Habit> {
        return habitDB.getAll()
    }


    fun getHabits(
        type: HabitType,
        nameStr: String = "",
        sorting: SortingParameter = SortingParameter(SortBy.PRIORITY, SortOrder.ASCENDING)
    ): List<Habit> {

        //делать через запроос SQL

        var habits = habitDB.getAll().filter {
            it.type == type.toString() && it.name.contains(nameStr)
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
        habitDB.insert(habit)
        changedHabits.postValue(habitDB.getAll())
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