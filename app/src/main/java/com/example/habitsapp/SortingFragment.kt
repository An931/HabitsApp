package com.example.habitsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_creation_habit.*
import kotlinx.android.synthetic.main.fragment_sorting_bottom_sheet.*

class SortingFragment(private var viewModel: HabitsPageViewModel, private val habitsModel: HabitsModel, private val type: HabitType) :
    BottomSheetDialogFragment() {

    companion object {
        fun newInstance(viewModel: HabitsPageViewModel, habitsModel: HabitsModel, type: HabitType): SortingFragment {
            Log.d("SortingFragment", "newInstance")
            return SortingFragment(viewModel, habitsModel, type)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
//            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                return HabitsPageViewModel(habitsModel, type) as T
//            }
//        }).get(HabitsPageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_sorting_bottom_sheet, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sortButton.setOnClickListener {
            val nameStr = nameStrEdit.text.toString()
            val sortBy = when (sortBySpinner.selectedItem.toString().toLowerCase()) {
                "name" -> SortBy.NAME
                "priority" -> SortBy.PRIORITY
                else -> SortBy.NAME
            }
            val sortOrder = if (radio_ascending.isChecked)
                SortOrder.ASCENDING
            else
                SortOrder.DESCENDING
            viewModel.changeSorting(nameStr, SortingParameter(sortBy, sortOrder))
            this.dismiss()
        }
    }

}