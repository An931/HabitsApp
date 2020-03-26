package com.example.habitsapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment


class HabitsPageFragment(val type: HabitType, val habits: List<Habit>) : Fragment() {

    val TAG = "PageFragment"
    val RED = "#ffe1da"
    val GREEN = "#daffdd"


    companion object {
        fun newInstance(
            type: HabitType,
            habits: List<Habit>
        ): HabitsPageFragment {
            Log.d("HabitsPageFragment", "newInstance")
            return HabitsPageFragment(type, habits)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.habits_page_fragment, container, false)
        val color = when (type) {
            HabitType.Good -> GREEN
            HabitType.Bad -> RED
            HabitType.Neutral -> "White"
        }
        view.setBackgroundColor(Color.parseColor(color))
        val layout = view.findViewById<LinearLayout>(R.id.habitsLayout)
        habits.forEach {
            val view = it.getView(context ?: MainActivity())
//            view.setOnClickListener {
//
//            }
            layout.addView(view)
        }

        Log.d(TAG, "onCreateView")
        return view
    }
}

