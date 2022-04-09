package com.development.nest.studyshechdular.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.development.nest.studyshechdular.DatabaseHelper.NoteDbHelper
import com.development.nest.studyshechdular.R

class CreateNoteActivity : AppCompatActivity() {
    lateinit var btnSave: Button
    lateinit var editTextNewNote: EditText
    var dbHelper: NoteDbHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        btnSave = findViewById(R.id.btnSave)
        editTextNewNote = findViewById(R.id.editTextNewNote)

        dbHelper = NoteDbHelper(this)

        btnSave.setOnClickListener {
            val note = editTextNewNote.text.toString()
            if (!TextUtils.isEmpty(note)) {
                if (dbHelper!!.addData(note)) {
                    Toast.makeText(applicationContext, "Note added", Toast.LENGTH_SHORT).show()
                    finish()
                    startActivity(Intent(this@CreateNoteActivity, NotesActivity::class.java))
                } else {
                    Toast.makeText(applicationContext, "Something wrong", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "Can't be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}