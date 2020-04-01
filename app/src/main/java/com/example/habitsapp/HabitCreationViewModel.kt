package com.example.habitsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HabitCreationViewModel(
    private val habitsModel: HabitsModel,
    private val changingHabit: Habit? = null
) : ViewModel() {

    private val mutableHabit: MutableLiveData<Habit?> = MutableLiveData()
    val habit: LiveData<Habit?> = mutableHabit

    init {
        load()
    }

    private fun load() {
        mutableHabit.postValue(changingHabit)
    }

    fun save(habit: Habit) = habitsModel.save(habit)
}