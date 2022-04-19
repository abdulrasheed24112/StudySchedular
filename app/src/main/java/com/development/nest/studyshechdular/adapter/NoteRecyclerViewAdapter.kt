package com.development.nest.studyshechdular.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.development.nest.studyshechdular.R
import com.development.nest.studyshechdular.models.Note


class NoteRecyclerViewAdapter(
    val mContext: Context,
    val list: ArrayList<Note>
): RecyclerView.Adapter<NoteRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var textViewNote: TextView? = null
        var parentLayout: ConstraintLayout? = null
        init {
            parentLayout = ItemView.findViewById<ConstraintLayout>(R.id.parent_layout)
            textViewNote = ItemView.findViewById<TextView>(R.id.textViewNote)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_note_single,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteRecyclerViewAdapter.ViewHolder, position: Int) {
        val schedule=list[position]
        holder.textViewNote!!.setText(schedule.note)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}