package com.development.nest.studyshechdular.ui

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.nest.studyshechdular.DatabaseHelper.NoteDbHelper
import com.development.nest.studyshechdular.R
import com.development.nest.studyshechdular.adapter.NoteRecyclerViewAdapter
import com.development.nest.studyshechdular.models.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesActivity : AppCompatActivity() {
    lateinit var fab: FloatingActionButton
    var notes: ArrayList<Note>? = null
    var noteDbHelper: NoteDbHelper? = null
    var recyclerView: RecyclerView? = null
    var back: ImageView? = null
    var not_found: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        noteDbHelper = NoteDbHelper(this)
        notes = noteDbHelper!!.getAll()
        not_found=findViewById(R.id.no_notes_found)
        back = findViewById(R.id.back_notes)
        back?.setOnClickListener {
            onBackPressed()
        }
        fab = findViewById(R.id.floatingActionButton)
        recyclerView = findViewById(R.id.recyclerView)

        fab.setOnClickListener {
            startActivity(
                Intent(
                    this@NotesActivity, CreateNoteActivity::class.java
                )
            )
            finish()
        }

        initRecylcerView()
    }

    private fun initRecylcerView() {

        val recyclerViewAdapter = NoteRecyclerViewAdapter(this, notes!!)
        recyclerView!!.adapter = recyclerViewAdapter
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        val swipeHelper: SwipeHelper = object : SwipeHelper(this, recyclerView) {
            override fun instantiateUnderlayButton(
                viewHolder: RecyclerView.ViewHolder?,
                underlayButtons: MutableList<UnderlayButton?>
            ) {
                underlayButtons.add(UnderlayButton(
                    "Edit",
                    0,
                    Color.parseColor("#C7C7CB")
                ) { pos ->
                    val intent = Intent(applicationContext, NoteUpdateActivity::class.java)
                    intent.putExtra("id", notes!![pos].id)
                    intent.putExtra("note", notes!![pos].note)
                    startActivity(intent)
                })
                underlayButtons.add(UnderlayButton(
                    "Delete",
                    0,
                    Color.parseColor("#FF3C30")
                ) { pos ->
                    val dialog = android.app.AlertDialog.Builder(this@NotesActivity)
                    dialog.setMessage("Are you sure?")
                    dialog.setTitle("Note delete")
                    dialog.setIcon(android.R.drawable.ic_dialog_alert)
                    dialog.setCancelable(false)
                    dialog.setPositiveButton("yes"
                    ) { _, _ ->
                        if (noteDbHelper!!.deleteById(notes!![pos].id)) {
                            Toast.makeText(
                                applicationContext,
                                "Note Deleted Successfully",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            notes!!.removeAt(pos)
                            recyclerViewAdapter.notifyItemRemoved(pos)
                        }
                    }
                    dialog.setNegativeButton("No"
                    ) { dialogInterface, _ -> dialogInterface.cancel() }
                    dialog.show()
                })
            }
        }
        if (recyclerViewAdapter.itemCount > 0) {
            not_found?.visibility = View.INVISIBLE
        } else {
            not_found?.visibility = View.VISIBLE


        }
    }

}