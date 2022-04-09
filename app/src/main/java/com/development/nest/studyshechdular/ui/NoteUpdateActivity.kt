package com.development.nest.studyshechdular.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.development.nest.studyshechdular.DatabaseHelper.NoteDbHelper
import com.development.nest.studyshechdular.R

class NoteUpdateActivity : AppCompatActivity() {
    lateinit var editTextUpdateNote: EditText
    lateinit var btnUpdate: Button
    var noteDbHelper: NoteDbHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_update)
        noteDbHelper = NoteDbHelper(this)

        btnUpdate = findViewById(R.id.btnUpdate)
        editTextUpdateNote = findViewById(R.id.editTextUpdateNote)

        val note = intent.getStringExtra("note")
        val id = intent.getStringExtra("id")
        editTextUpdateNote!!.setText(note)

        btnUpdate!!.setOnClickListener(View.OnClickListener {
            val updateNote = editTextUpdateNote!!.text.toString()
            if (noteDbHelper!!.updateById(id!!, updateNote)) {
                Toast.makeText(applicationContext, "Note updated", Toast.LENGTH_SHORT).show()
                finish()
                startActivity(Intent(this@NoteUpdateActivity, NotesActivity::class.java))
            } else {
                Toast.makeText(applicationContext, "Something wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
}