package com.example.habitsapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AboutAppFragment : DialogFragment(){
    companion object {
        fun newInstance(): AboutAppFragment {
            return AboutAppFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_about, container, false)
//        val color = when (type) {
//            HabitType.Good -> "#daffdd"
//            HabitType.Bad -> "#ffe1da"
//            HabitType.Neutral -> "#fff"
//        }
//        view.setBackgroundColor(Color.parseColor(color))
        return view
    }

//    override fun getTheme(): Int {
//        return R.style.HabitCreateTheme
//    }
}