package com.example.habitsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Observer

class HabitsPageViewModel(private val habitsModel: HabitsModel, private val type: HabitType) :
    ViewModel() {


    private val mutableHabits: MutableLiveData<List<Habit>?> = MutableLiveData()
    val habits: LiveData<List<Habit>?> = mutableHabits

    var sorting :SortingParameter = SortingParameter(SortBy.PRIORITY, SortOrder.ASCENDING)

    init {
        habitsModel.habitDB.getAll().observeForever(Observer {
//            mutableHabits.postValue(it.filter { it.type == type.toString() })
            mutableHabits.postValue(habitsModel.getHabits(it, type, "", sorting))
        })
    }


    fun changeSorting(nameStr: String, newSorting: SortingParameter){
        sorting = newSorting
        val allHabits = habitsModel.habitDB.getAllFrozenData()
        mutableHabits.postValue(habitsModel.getHabits(allHabits, type, nameStr, newSorting))
    }
}