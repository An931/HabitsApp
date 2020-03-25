package com.example.habitsapp

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class HabitsFragmentPagerAdapter(
    fm: FragmentManager,
    private val context: Context
) : FragmentPagerAdapter(fm) {

    val TAG = "HFPagerAdapter"

    val PAGE_COUNT = 2
    private val tabTitles = arrayOf("Bad", "Good")
//    private val colors = mapOf("Bad" to "#ffe1da", "Good" to "#daffdd")
    private val types = mapOf("Bad" to HabitType.Bad, "Good" to HabitType.Good)

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment {
        Log.d(TAG, "getItem")
        val type = types[tabTitles[position]]?: HabitType.Neutral
        return HabitsPageFragment.newInstance(type)
//        return HabitsPageFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): String {
        // генерируем заголовок в зависимости от позиции
        return tabTitles[position]
    }
}