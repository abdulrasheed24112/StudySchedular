package com.development.nest.studyshechdular.ui

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.nest.studyshechdular.DatabaseHelper.ScheduleDbHelper
import com.development.nest.studyshechdular.R
import com.development.nest.studyshechdular.adapter.ScheduleRecyclerViewAdapter
import com.development.nest.studyshechdular.models.Schedule
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class SchedulerActivity : AppCompatActivity() {
    var fab: FloatingActionButton? = null
    var schedules: ArrayList<Schedule>? = null
    var scheduleDbHelper: ScheduleDbHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scheduler)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.setDisplayShowHomeEnabled(true)
        scheduleDbHelper = ScheduleDbHelper(this)
        schedules = scheduleDbHelper!!.getAll()

        fab = findViewById(R.id.fab)

        fab!!.setOnClickListener(View.OnClickListener { view ->
            Snackbar.make(view, "New", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
              startActivity(Intent(this@SchedulerActivity, NewScheduleActivity::class.java))
        })

        initRecylcerView()
    }
    private fun initRecylcerView() {
        val recyclerView: RecyclerView = findViewById(R.id.scheduleListView)
        val recyclerViewAdapter = ScheduleRecyclerViewAdapter(this, schedules!!)
        recyclerView.setAdapter(recyclerViewAdapter)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        val swipeHelper: SwipeHelper = object : SwipeHelper(this, recyclerView) {
            override fun instantiateUnderlayButton(viewHolder: RecyclerView.ViewHolder?, underlayButtons: MutableList<UnderlayButton?>) {
                underlayButtons.add(UnderlayButton(
                        "Edit",
                        0,
                        Color.parseColor("#C7C7CB")
                ) { pos ->
                    val intent = Intent(applicationContext, ScheduleUpdateActivity::class.java)
                    intent.putExtra("id", schedules!![pos].id)
                    intent.putExtra("day", schedules!![pos].day)
                    intent.putExtra("item", schedules!![pos].item)
                    intent.putExtra("subject", schedules!![pos].subject)
                    intent.putExtra("time", schedules!![pos].time)
                    startActivity(intent)
                })
                underlayButtons.add(UnderlayButton(
                        "Delete",
                        0,
                        Color.parseColor("#FF3C30")
                ) { pos ->
                    val dialog: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this@SchedulerActivity)
                    dialog.setMessage("Are you sure?")
                    dialog.setTitle("Note delete")
                    dialog.setIcon(android.R.drawable.ic_dialog_alert)
                    dialog.setCancelable(false)
                    dialog.setPositiveButton("yes", DialogInterface.OnClickListener { dialogInterface, i ->
                        if (scheduleDbHelper!!.deleteById(schedules!![pos].id)) {
                            Toast.makeText(applicationContext, "Schedule Deleted", Toast.LENGTH_SHORT).show()
                            schedules!!.removeAt(pos)
                            recyclerViewAdapter.notifyItemRemoved(pos)
                        }
                    })
                    dialog.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.cancel() })
                    dialog.show()
                })
            }
        }
    }
}