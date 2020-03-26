package com.example.habitsapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_creation_habit.*


class HabitCreationFragment(val callback:HabitCreationCallback) : DialogFragment(){

    companion object{
        fun newInstance(callback:HabitCreationCallback):HabitCreationFragment {
            return HabitCreationFragment(callback)
        }
    }

//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_creation_habit, container, false)

        return view
    }

    override fun getTheme(): Int {
        return R.style.HabitCreateTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveHabitButton.setOnClickListener{
            val name = nameEdit.text.toString()
            val description = descriptionEdit.text.toString()
            val priority = prioritySpinner.selectedItem.toString()
            val type = getView()?.findViewById<RadioButton>( typeRadioGroup.checkedRadioButtonId)?.text.toString()
            val periodicity = periodicityEdit.text.toString()
            val color = colorSpinner.selectedItem.toString()
            val habit = Habit(
                name,
                description,
                priority.toInt(),
                HabitType.valueOf(type),
                periodicity,
                color
            )
            callback.onHabitSave(habit)
            dismiss()
        }
    }
}

interface HabitCreationCallback{
    fun onHabitSave(habit: Habit)
}