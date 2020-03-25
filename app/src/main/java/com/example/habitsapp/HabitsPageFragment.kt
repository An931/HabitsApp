package com.example.habitsapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment


class HabitsPageFragment(val type: HabitType) :Fragment(){
//    val ARG_PAGE = "ARG_PAGE"
//    private var mPage = 0
    val TAG = "PageFragment"
    val RED = "#ffe1da"
    val GREEN = "#daffdd"

    //private val dbOpenHelper = DbOpenHelper() //?????

    companion object{
        fun newInstance(type: HabitType):HabitsPageFragment {
            Log.d("newInstance", "" )
            return HabitsPageFragment(type)
        }
    }

//    public static PageFragment newInstance(int page) {
//        Bundle args = new Bundle();
//        args.putInt(ARG_PAGE, page);
//        PageFragment fragment = new PageFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }


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
        val color = when(type){
            HabitType.Good -> GREEN
            HabitType.Bad -> RED
            HabitType.Neutral -> "White"
        }
        view.setBackgroundColor(Color.parseColor(color))
//        val b = view.findViewById<TextView>(R.id.text)
//        b.text = "rtrtr"
//        val layout = view.findViewById<LinearLayout>(R.id.habitsLayout)
//        val habits = dbOpenHelper.getAllHabits()
//
//        habits.forEach { layout.addView(it.getView(MainActivity())) } ////MainActivity() ?????

        Log.d(TAG, "onCreateView")
        return view
    }
}
