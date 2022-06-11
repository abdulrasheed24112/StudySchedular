package com.development.nest.studyshechdular.DatabaseHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.development.nest.studyshechdular.Constants.DB
import com.development.nest.studyshechdular.models.Note

class NoteDbHelper(context: Context?) : SQLiteOpenHelper(context, DB.NOTES_TABLE_NAME, null, 1) {
    private val context: Context? = null
    override fun onCreate(db: SQLiteDatabase) {
        val query =
            "CREATE TABLE " + DB.NOTES_TABLE_NAME.toString() + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NOTE + " TEXT)"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DB.NOTES_TABLE_NAME)
        onCreate(db)
    }

    fun addData(note: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NOTE, note)
        val result = db.insert(DB.NOTES_TABLE_NAME, null, contentValues)
        return result != -1L
    }

    fun getAll(): ArrayList<Note> {
        val notes: ArrayList<Note> = ArrayList<Note>()
        val db = this.readableDatabase
        val query = "SELECT * FROM " + DB.NOTES_TABLE_NAME
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0).toString()
            val text = cursor.getString(1)
            val note = Note(id, text)
            notes.add(note)
        }
        cursor.close()
        return notes
    }

    fun deleteById(id: String): Boolean {
        val db = this.readableDatabase
        return db.delete(DB.NOTES_TABLE_NAME, "ID=?", arrayOf(id)) > 0
    }

    fun updateById(id: String, note: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NOTE, note)
        return db.update(DB.NOTES_TABLE_NAME, contentValues, "ID=$id", null) > 0
    }

    companion object {
        private const val COL_ID = "ID"
        private const val COL_NOTE = "note"
    }

    init {
        var context = context
        context = context
    }
}
