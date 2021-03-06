package com.masterdev.agehiapp

import android.app.Dialog
import android.content.Intent
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.masterdev.agehiapp.database.DatabaseHandler
import com.masterdev.agehiapp.database.RecyclerActivity
import com.masterdev.agehiapp.database.User

class MainActivity : AppCompatActivity() {

    private lateinit var btnAdd: Button
    private lateinit var btnDelete: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnRead: Button
    private lateinit var btnShowAll: Button
    private lateinit var edt: EditText
    private lateinit var idUpdate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val databaseHandler = DatabaseHandler(this)

        btnAdd = findViewById(R.id.btnAdd)
        btnDelete = findViewById(R.id.btn_delete)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnRead = findViewById(R.id.btnRead)
        btnShowAll = findViewById(R.id.show_all)
        edt = findViewById(R.id.editTextTextPersonName)
        idUpdate = findViewById(R.id.editTextTextPersonName2)

        btnAdd.setOnClickListener {
            val username = edt.text.toString()
            val user = User(username)

            databaseHandler.addUser(user)

        }

        btnDelete.setOnClickListener {

            val id = edt.text.toString()
            showDialog(id,databaseHandler)

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

        btnRead.setOnClickListener {
            val id = idUpdate.text
            var username = databaseHandler.getUserById(id.toString().toInt())
            Toast.makeText(this, username, Toast.LENGTH_SHORT).show()
        }

        btnShowAll.setOnClickListener {
            val intent = Intent(this, RecyclerActivity::class.java)
            startActivity(intent)
        }

    }


    private fun showDialog(id:String, database:DatabaseHandler){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        val btnY = dialog.findViewById<Button>(R.id.btnYes)
        val itemQuestion = dialog.findViewById<TextView>(R.id.textViewB)
        itemQuestion.text = id
        btnY.setOnClickListener {
            database.removeUser(id)
            dialog.dismiss()
        }

        val btnN = dialog.findViewById<Button>(R.id.btnNo)
        btnN.setOnClickListener {
            dialog.cancel()
        }

        dialog.show()

        val window = dialog.window
        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
    }
}