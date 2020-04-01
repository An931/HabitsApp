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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class HabitsPageFragment(private val habitsModel : HabitsModel, val type: HabitType) : Fragment() {

    val TAG = "PageFragment"

    private lateinit var viewModel: HabitsPageViewModel

    companion object {
        fun newInstance(habitsModel : HabitsModel, type: HabitType): HabitsPageFragment {
            Log.d("HabitsPageFragment", "newInstance")
            return HabitsPageFragment(habitsModel, type)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HabitsPageViewModel(habitsModel, type) as T
            }
        }).get(HabitsPageViewModel::class.java)
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
            HabitType.Good -> "#daffdd"
            HabitType.Bad -> "#ffe1da"
            HabitType.Neutral -> "#fff"
        }
        view.setBackgroundColor(Color.parseColor(color))

//        val layout = view.findViewById<LinearLayout>(R.id.habitsLayout)
//        habits.forEach {
//            val view = it.getView(context ?: MainActivity())
////            view.setOnClickListener {
////
////            }
//            layout.addView(view)
//        }

        Log.d(TAG, "onCreateView")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
        val layout = view.findViewById<LinearLayout>(R.id.habitsLayout)
        viewModel.habits.observe(this, Observer { habits ->
            habits?.forEach {
                val view = it.getView(context ?: MainActivity())
//            view.setOnClickListener {
//
//            }
                Log.d(TAG, "onViewCreated")
                layout.addView(view)
            }
        })
        Log.d(TAG, "onViewCreated")
    }
}

