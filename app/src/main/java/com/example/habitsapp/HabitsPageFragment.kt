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
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.habits_page_fragment.*


class HabitsPageFragment(private val habitsModel: HabitsModel, val type: HabitType) : Fragment() {

    val TAG = "PageFragment"

    private lateinit var viewModel: HabitsPageViewModel

    companion object {
        fun newInstance(habitsModel: HabitsModel, type: HabitType): HabitsPageFragment {
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
        Log.d(TAG, "onCreateView")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val layout = view.findViewById<LinearLayout>(R.id.habitsLayout)
//        viewModel.habits.observe(this, Observer { habits ->
//            layout.removeAllViews()
//            habits?.forEach {
//                val view = it.getView(context ?: MainActivity())
//                layout.addView(view)
//            }
//        })
        recycler_view.layoutManager = LinearLayoutManager(this.activity)
        viewModel.habits.observe(this, Observer { habits ->
//            layout.removeAllViews()
            recycler_view.adapter = RecyclerViewAdapter(viewModel.habits.value?: listOf())
        })

        search_button.setOnClickListener {
            val sortBottomSheet = SortingFragment.newInstance(viewModel, habitsModel, type)
            sortBottomSheet.show(fragmentManager, "")
        }


//        val transaction = getChildFragmentManager().beginTransaction()
//        transaction.replace(R.id.sorting_fragment_container, sortBottomSheet).commit();
//
//        val sortBottomSheet = SortingFragment(habitsModel, type)
//        val transaction = fragmentManager?.beginTransaction()
//        transaction?.add(R.id.sorting_fragment_container, sortBottomSheet);
//        transaction?.commit();


        Log.d(TAG, "onViewCreated")
    }
}

