package com.example.habitsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Observer

class HabitsPageViewModel(private val habitsModel: HabitsModel, private val type: HabitType) :
    ViewModel() {


    private val mutableHabits: MutableLiveData<List<Habit>?> = MutableLiveData()
    //private val mutableIsDataLoading: MutableLiveData<Boolean?> = MutableLiveData()

    val habits: LiveData<List<Habit>?> = mutableHabits
    //val isDataLoading: LiveData<Boolean?> = mutableIsDataLoading

    init {
        load()
//        habitsModel.habitsChanged.observeForever( Observer{
//            if (habitsModel.habitsChanged.value == true){
//                load()
//                habitsModel.habitsChanged.value = false
//            }
//        })
        habitsModel.changedHabits.observeForever(Observer {
            load()
        })
    }

    private fun load() {
        //mutableIsDataLoading.value = true

//        model.loadProfileAsync(profileId) { loadedProfile: Profile ->
//            mutableIsDataLoading.postValue(false)
//            mutableProfile.postValue(loadedProfile)
//        }
        mutableHabits.postValue(habitsModel.getHabits(type))
    }

    fun changeSorting(nameStr: String, sorting: SortingParameter){
        mutableHabits.postValue(habitsModel.getHabits(type, nameStr, sorting))
    }
}