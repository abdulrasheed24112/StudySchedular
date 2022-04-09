package com.development.nest.studyshechdular.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.development.nest.studyshechdular.DatabaseHelper.ScheduleDbHelper
import com.development.nest.studyshechdular.R
import kotlin.collections.ArrayList

class ScheduleUpdateActivity : AppCompatActivity() {
    lateinit var daySpinnerUpdate: Spinner
    lateinit var itemSpinnerUpdate: Spinner
    lateinit var updateButton: Button
    lateinit var timePickerUpdate: TimePicker
    private val scheduleDbHelper: ScheduleDbHelper? = null
    lateinit var subjectTextViewUpdate: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_update)
        daySpinnerUpdate = findViewById(R.id.daySpinnerUpdate)
        itemSpinnerUpdate = findViewById(R.id.itemSpinnerUpdate)
        updateButton = findViewById(R.id.updateButton)
        timePickerUpdate = findViewById(R.id.timePickerUpdate)
        subjectTextViewUpdate = findViewById(R.id.subjectTextViewUpdate)

        val id = intent.getStringExtra("id")
        val day = intent.getStringExtra("day")
        val item = intent.getStringExtra("item")
        val subject = intent.getStringExtra("subject")
        val time = intent.getStringExtra("time")

        subjectTextViewUpdate.text = subject


        // setting up spinners
        setupSpinners()

        updateButton.setOnClickListener {
            val day = daySpinnerUpdate.selectedItem.toString()
            val item = itemSpinnerUpdate.selectedItem.toString()
            val subject = subjectTextViewUpdate.text.toString()
            val time =
                timePickerUpdate.currentHour.toString() + " : " + timePickerUpdate.currentMinute.toString()
            if (!TextUtils.isEmpty(day) && !TextUtils.isEmpty(subject) && !TextUtils.isEmpty(item) && !TextUtils.isEmpty(
                    time
                )
            ) {
                if (scheduleDbHelper!!.updateById(id!!, day, item, subject, time)) {
                    Toast.makeText(applicationContext, "Schedule updated", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                    startActivity(Intent(applicationContext, SchedulerActivity::class.java))
                } else {
                    Toast.makeText(applicationContext, "Something wrong", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "All field are required", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
    fun setupSpinners() {
        val days: ArrayList<String> = ArrayList()
        days.add("Saturday")
        days.add("Sunday")
        days.add("Monday")
        days.add("Tuesday")
        days.add("Wednesday")
        days.add("Thursday")
        days.add("Friday")
        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        daySpinnerUpdate.adapter = dataAdapter
        val items: MutableList<String> = ArrayList()
        items.add("Assignment")
        items.add("Class test")
        items.add("Viva")
        items.add("Notes")
        items.add("Interview")
        items.add("Mid Term Examination")
        items.add("Final Examination")
        val dataAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        itemSpinnerUpdate.adapter = dataAdapter2
    }
}