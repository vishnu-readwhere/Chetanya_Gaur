package com.example.assignmentchetanyagaur.ui.theme

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "DATABASE_NAME", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT, phone TEXT, email TEXT, dob TEXT, image TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertUser(name: String, phone: String, email: String, dob: String, image: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("name", name)
            put("phone", phone)
            put("email", email)
            put("dob", dob)
            put("image", image)
        }
        val result = db.insert("users", null, contentValues)
        return result != -1L
    }

    fun getAllUsers(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM users", null)
    }

    fun updateUser(id: Int, name: String, phone: String, email: String, dob: String, image: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("name", name)
            put("phone", phone)
            put("email", email)
            put("dob", dob)
            put("image", image)
        }
        return db.update("users", contentValues, "id=?", arrayOf(id.toString())) > 0
    }

    fun deleteUser(id: Int): Boolean {
        val db = this.writableDatabase
        return db.delete("users", "id=?", arrayOf(id.toString())) > 0
    }
}
