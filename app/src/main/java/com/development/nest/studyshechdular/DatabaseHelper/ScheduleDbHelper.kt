package com.development.nest.studyshechdular.DatabaseHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.development.nest.studyshechdular.Constants.DB
import com.development.nest.studyshechdular.models.Schedule
import java.util.*


class ScheduleDbHelper(context: Context?) : SQLiteOpenHelper(context, DB.TABLE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE " + DB.TABLE_NAME.toString() + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT)"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DB.TABLE_NAME)
        onCreate(db)
    }

    fun addData(day: String?, item: String?, subject: String?, time: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL2, day)
        contentValues.put(COL3, item)
        contentValues.put(COL4, subject)
        contentValues.put(COL5, time)
        val result = db.insert(DB.TABLE_NAME, null, contentValues)
        return result != -1L
    }

    public fun getAll(): ArrayList<Schedule>  {
            val schedules: ArrayList<Schedule> = ArrayList<Schedule>()
            val db = this.readableDatabase
            val query = "SELECT * FROM " + DB.TABLE_NAME
            val cursor = db.rawQuery(query, null)
            while (cursor.moveToNext()) {
                val id = cursor.getInt(0).toString()
                val day = cursor.getString(1)
                val item = cursor.getString(2)
                val subject = cursor.getString(3)
                val time = cursor.getString(4)
               // val schedule = Schedule(id, day, item, subject, time)
                val schedule = Schedule()
                schedule.id=id
                schedule.day=day
                schedule.item=item
                schedule.subject=subject
                schedule.time=time

                schedules.add(schedule)
            }
            cursor.close()
            return schedules
        }

    fun deleteById(id: String): Boolean {
        val db = this.readableDatabase
        return db.delete(DB.TABLE_NAME, "ID=?", arrayOf(id)) > 0
    }

    fun updateById(id: String, day: String?, item: String?, subject: String?, time: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL2, day)
        contentValues.put(COL3, item)
        contentValues.put(COL4, subject)
        contentValues.put(COL5, time)
        return db.update(DB.TABLE_NAME, contentValues, "ID=$id", null) > 0
    }

    companion object {
        private const val COL1 = "ID"
        private const val COL2 = "day"
        private const val COL3 = "item"
        private const val COL4 = "subject"
        private const val COL5 = "time"
    }
}
