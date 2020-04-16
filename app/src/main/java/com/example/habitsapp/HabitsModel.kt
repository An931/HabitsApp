package com.example.habitsapp

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Room

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HabitsModel(val habitDB: HabitDao) : CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + CoroutineExceptionHandler { _, e -> throw e }

    fun getHabits(): List<Habit>? {
        return habitDB.getAll().value
    }


    fun getHabits(
        habits: List<Habit>?,
        type: HabitType,
        nameStr: String = "",
        sorting: SortingParameter = SortingParameter(SortBy.PRIORITY, SortOrder.ASCENDING)
    ): List<Habit>? {

        //делать через запроос SQL

        var habits = habits?.filter {
            //why null
            it.type == type.toString() && it.name.contains(nameStr)
        }
        when (sorting.sortBy) {
            SortBy.NAME -> habits = habits?.sortedBy { it.name }
            SortBy.PRIORITY -> habits =
                habits?.sortedWith(compareBy<Habit> { it.priority }.thenBy { it.name })
//            SortBy.TIME -> habits = habits.sortedBy { it.name }
        }
        if (sorting.order == SortOrder.DESCENDING)
            habits = habits?.reversed()

        return habits
    }

//    fun save(habit: Habit) {
//        if (habit.id == Habit.INVALID_ID) {
//            habitDB.insert(habit)
//        }
//        else {
//            habitDB.update(habit)
//        }
//
//    }

    fun save(habit: Habit) = launch {
        if (habit.id == 0)
            withContext(Dispatchers.IO) {
                habitDB.insert(habit)
            }
        else {
            withContext(Dispatchers.IO) { habitDB.update(habit) }
        }
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