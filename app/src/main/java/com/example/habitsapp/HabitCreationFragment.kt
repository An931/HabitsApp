package com.example.habitsapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_creation_habit.*
import kotlinx.android.synthetic.main.fragment_creation_habit.descriptionEdit
import kotlinx.android.synthetic.main.fragment_creation_habit.nameEdit
import kotlinx.android.synthetic.main.fragment_creation_habit.periodicityEdit
import kotlinx.android.synthetic.main.fragment_creation_habit.prioritySpinner
import kotlinx.android.synthetic.main.fragment_creation_habit.radio_bad
import kotlinx.android.synthetic.main.fragment_creation_habit.radio_good
import kotlinx.android.synthetic.main.fragment_creation_habit.saveHabitButton
import kotlinx.android.synthetic.main.fragment_creation_habit.typeRadioGroup
import kotlinx.android.synthetic.main.fragment_creation_habit.habitId


class HabitCreationFragment(private val habitsModel: HabitsModel, val habit: Habit? = null) : DialogFragment(){

    private lateinit var viewModel: HabitCreationViewModel

    companion object{
        fun newInstance(habitsModel: HabitsModel):HabitCreationFragment {
            return HabitCreationFragment(habitsModel)
        }
        fun newInstance(habitsModel: HabitsModel, habit: Habit):HabitCreationFragment {
            return HabitCreationFragment(habitsModel, habit)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_creation_habit, container, false)

        if (habit != null){
            nameEdit.setText( habit.name)
            descriptionEdit.setText(habit.descriptor)
            prioritySpinner.setSelection(habit.priority-1)
            when(habit.type){
                HabitType.Good -> radio_good.isChecked = true
                HabitType.Bad-> radio_bad.isChecked=true
            }
            periodicityEdit.setText(habit.periodicity)
            habitId.text = habit.id.toString()
        }

        return view
    }

    override fun getTheme(): Int {
        return R.style.HabitCreateTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveHabitButton.setOnClickListener{
            if (!(nameEdit.text.isEmpty()
                        || descriptionEdit.text.isEmpty()
                        || periodicityEdit.text.isEmpty())
            ) {
                val name = nameEdit.text.toString()
                val description = descriptionEdit.text.toString()
                val priority = prioritySpinner.selectedItem.toString()
                val type = getView()?.findViewById<RadioButton>(typeRadioGroup.checkedRadioButtonId)
                    ?.text.toString()
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
                habit.id = habitId.text.toString().toLong()
                habitsModel.save(habit)
                dismiss()
            }
        }
    }
}

//interface HabitCreationCallback{
//    fun onHabitSave(habit: Habit, context:Context?)
//}