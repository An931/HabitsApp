package com.example.habitsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HabitsPageViewModel(private val habitsModel : HabitsModel, private val type : HabitType) :ViewModel(){

    private val mutableHabits: MutableLiveData<List<Habit>?> = MutableLiveData()
    //private val mutableIsDataLoading: MutableLiveData<Boolean?> = MutableLiveData()

    val habits: LiveData<List<Habit>?> = mutableHabits
    //val isDataLoading: LiveData<Boolean?> = mutableIsDataLoading

    init {
        load()
    }

    private fun load() {
        //mutableIsDataLoading.value = true

//        model.loadProfileAsync(profileId) { loadedProfile: Profile ->
//            mutableIsDataLoading.postValue(false)
//            mutableProfile.postValue(loadedProfile)
//        }
        mutableHabits.postValue(habitsModel.getHabits(type))
    }
}
//class ProfileViewModel(private val model: Model, private val profileId: String) : ViewModel() {
//
//    private val mutableProfile: MutableLiveData<Profile?> = MutableLiveData()
//    private val mutableIsDataLoading: MutableLiveData<Boolean?> = MutableLiveData()
//
//    val profile: LiveData<Profile?> = mutableProfile
//    val isDataLoading: LiveData<Boolean?> = mutableIsDataLoading
//
//    init {
//        load()
//    }
//
//    private fun load() {
//
//        mutableIsDataLoading.value = true
//
//        model.loadProfileAsync(profileId) { loadedProfile: Profile ->
//            mutableIsDataLoading.postValue(false)
//            mutableProfile.postValue(loadedProfile)
//
//        }
//    }
//}