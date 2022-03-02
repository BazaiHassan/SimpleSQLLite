package com.masterdev.agehiapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, "MyDatabase", null, 1) {

    // Everything in this type of object is not supposed to be changed
    companion object {
        val TABLE_USER = "user"
        val ID = "id"
        val USERNAME = "username"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "create table $TABLE_USER ($ID integer primary key autoincrement, $USERNAME text)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun addUser(user: User) {
        var db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(USERNAME, user.username)
        db.insert(TABLE_USER, null, contentValues)
        //db.close()
    }

    fun removeUser(id:String){
        var db = this.writableDatabase
        db.delete(TABLE_USER,"${ID}=?", arrayOf(id))
    }

    fun updateUser(id: String, user: User){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID,id)
        contentValues.put(USERNAME, user.username)
        db.update(TABLE_USER, contentValues, "${ID}=?", arrayOf(id))
    }

    fun getUserById(id:Int):String{
        var query = "select * from $TABLE_USER where $ID = $id"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query,null)
        cursor.moveToNext()
        val username = cursor.getString(cursor.getColumnIndexOrThrow(USERNAME))

        return username
    }

}