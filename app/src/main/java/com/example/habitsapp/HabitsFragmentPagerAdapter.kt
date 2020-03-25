package com.example.habitsapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HabitsFragmentPagerAdapter(fm: FragmentManager,
                                 private val context: Context) : FragmentPagerAdapter(fm) {
    val PAGE_COUNT = 2
    private val tabTitles = arrayOf("Bad", "Good")


    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment {
        return HabitsPageFragment.newInstance()
//        return HabitsPageFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): String {
        // генерируем заголовок в зависимости от позиции
        return tabTitles[position]
    }
}