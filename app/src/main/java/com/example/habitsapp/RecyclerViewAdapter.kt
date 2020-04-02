package com.example.habitsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val values: List<Habit>) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return values.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTextView.text = values[position].name
        holder.descriptionTextView.text = values[position].descriptor
        holder.priorityTextView.text = values[position].priority.toString()
        holder.periodicityTextView.text = values[position].periodicity
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameTextView = itemView.findViewById<TextView>(R.id.name)
    val descriptionTextView = itemView.findViewById<TextView>(R.id.description)
    val priorityTextView = itemView.findViewById<TextView>(R.id.priority)
    val periodicityTextView = itemView.findViewById<TextView>(R.id.periodicity)

}