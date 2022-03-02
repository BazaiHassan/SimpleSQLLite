package com.masterdev.agehiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.masterdev.agehiapp.database.DatabaseHandler
import com.masterdev.agehiapp.database.User

class MainActivity : AppCompatActivity() {

    private lateinit var btnAdd: Button
    private lateinit var btnDelete: Button
    private lateinit var btnUpdate: Button
    private lateinit var edt: EditText
    private lateinit var idUpdate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val databaseHandler = DatabaseHandler(this)

        btnAdd = findViewById(R.id.btnAdd)
        btnDelete = findViewById(R.id.btn_delete)
        btnUpdate = findViewById(R.id.btnUpdate)
        edt = findViewById(R.id.editTextTextPersonName)
        idUpdate = findViewById(R.id.editTextTextPersonName2)

        btnAdd.setOnClickListener {
            val username = edt.text.toString()
            val user = User(username)

            databaseHandler.addUser(user)

        }

        btnDelete.setOnClickListener {
            val id = edt.text.toString()
            databaseHandler.removeUser(id)
        }

        btnUpdate.setOnClickListener {
            val id = idUpdate.text.toString()
            val username = edt.text.toString()
            val user = User(username)
            if (id == "" || username == ""){
                Toast.makeText(this, "Please Fill All fields.", Toast.LENGTH_SHORT).show()
            }else{
                databaseHandler.updateUser(id, user)
            }
        }

    }
}