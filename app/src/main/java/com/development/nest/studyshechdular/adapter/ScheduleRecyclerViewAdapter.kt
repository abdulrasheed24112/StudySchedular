package com.development.nest.studyshechdular.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.development.nest.studyshechdular.R
import com.development.nest.studyshechdular.models.Schedule


class ScheduleRecyclerViewAdapter(
    val mContext: Context,
    private val list: ArrayList<Schedule>
) : RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var newSubjectTextView: TextView = ItemView.findViewById(R.id.newSubjectTextView)
        var itemTextView: TextView = ItemView.findViewById(R.id.itemTextView)
        var dayTextView: TextView = ItemView.findViewById(R.id.dayTextView)
        var parent: ConstraintLayout = ItemView.findViewById(R.id.parent_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_schedule_single, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val schedule = list[position]
        holder.newSubjectTextView.text = schedule.subject
        holder.itemTextView.text = schedule.item
        holder.dayTextView.text = schedule.day
    }

    override fun getItemCount(): Int {
        return list.size
    }
}