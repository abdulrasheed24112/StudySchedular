package com.development.nest.studyshechdular.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.development.nest.studyshechdular.DatabaseHelper.ScheduleDbHelper
import com.development.nest.studyshechdular.R
import com.development.nest.studyshechdular.utils.hideKeyboard

class NewScheduleActivity : AppCompatActivity() {
    private var daySpinner: Spinner? = null
    private var itemSpinner: Spinner? = null
    lateinit var saveButton: Button
    lateinit var timePicker: TimePicker
    private var scheduleDbHelper: ScheduleDbHelper? = null

    lateinit var subjectTextView: TextView
    var back: ImageView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_schedule)
        scheduleDbHelper = ScheduleDbHelper(this)
        back = findViewById(R.id.back_new_schedule)
        back?.setOnClickListener {
            onBackPressed()
        }
        back?.hideKeyboard()
        daySpinner = findViewById(R.id.daySpinner)
        itemSpinner = findViewById(R.id.itemSpinner)
        saveButton = findViewById(R.id.saveButton)
        timePicker = findViewById(R.id.timePicker)
        subjectTextView = findViewById(R.id.subjectTextView)
        setupSpinners()
        saveButton.setOnClickListener(View.OnClickListener {
            val day = daySpinner!!.selectedItem.toString()
            val item = itemSpinner!!.selectedItem.toString()
            val subject = subjectTextView.text.toString()
            val time =
                timePicker.currentHour.toString() + " : " + timePicker.currentMinute
                    .toString()
            if (!TextUtils.isEmpty(day) && !TextUtils.isEmpty(subject) && !TextUtils.isEmpty(item) && !TextUtils.isEmpty(
                    time
                )
            ) {
                if (scheduleDbHelper!!.addData(day, item, subject, time)) {
                    Toast.makeText(applicationContext, "Schedule added", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, SchedulerActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Something wrong", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "All field are required", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun setupSpinners() {
        val weekDays: ArrayList<String> = ArrayList()
        weekDays.add("Saturday")
        weekDays.add("Sunday")
        weekDays.add("Monday")
        weekDays.add("Tuesday")
        weekDays.add("Wednesday")
        weekDays.add("Thursday")
        weekDays.add("Friday")
        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, weekDays)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        daySpinner!!.adapter = dataAdapter
        val items: ArrayList<String> = ArrayList()
        items.add("Assignment")
        items.add("Class test")
        items.add("Viva")
        items.add("Notes")
        items.add("Interview")
        items.add("Mid Term Examination")
        items.add("Final Examination")
        val dataAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        itemSpinner!!.adapter = dataAdapter2
    }

}