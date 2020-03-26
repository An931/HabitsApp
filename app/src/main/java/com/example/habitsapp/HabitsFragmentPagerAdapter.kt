package com.example.habitsapp

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class HabitsFragmentPagerAdapter(
    fm: FragmentManager,
    private val context: Context,
    private val habits: List<Habit>
) : FragmentPagerAdapter(fm) {

    val TAG = "HFPagerAdapter"

    val PAGE_COUNT = 2
    private val tabTitles = arrayOf("Good", "Bad")
//    private val colors = mapOf("Bad" to "#ffe1da", "Good" to "#daffdd")
    private val types = mapOf("Bad" to HabitType.Bad, "Good" to HabitType.Good)

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment {
        Log.d(TAG, "getItem")
        val type = types[tabTitles[position]]?: HabitType.Neutral
        val filtered = habits.filter { it.type==type }
        return HabitsPageFragment.newInstance(type, filtered)
    }

    override fun getPageTitle(position: Int): String {
        return tabTitles[position]
    }
}