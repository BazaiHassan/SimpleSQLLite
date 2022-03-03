package com.masterdev.agehiapp.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.masterdev.agehiapp.Adapter.ItemAdapter
import com.masterdev.agehiapp.Adapter.ItemClickListener
import com.masterdev.agehiapp.R

class RecyclerActivity : AppCompatActivity(), ItemClickListener {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        recyclerView = findViewById(R.id.rv_show)

        val itemAdapter = ItemAdapter(this)
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val databaseHandler = DatabaseHandler(this)
        val usernameArray = databaseHandler.getAllUsername()

        itemAdapter.loadedState(usernameArray)

    }

    override fun onItemClicked(user:String) {
        Toast.makeText(this, user, Toast.LENGTH_SHORT).show()
    }
}