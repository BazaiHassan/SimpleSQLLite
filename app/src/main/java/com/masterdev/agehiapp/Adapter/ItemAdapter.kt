package com.masterdev.agehiapp.Adapter

import android.content.Context
import android.widget.TextView
import com.masterdev.agehiapp.R
import com.masterdev.agehiapp.database.User
import io.github.farshidroohi.AdapterRecyclerView

class ItemAdapter(val itemClickListener: ItemClickListener):AdapterRecyclerView<String>(R.layout.item_user,0,0,0) {
    override fun onBindView(
        viewHolder: ItemViewHolder,
        position: Int,
        context: Context,
        element: String?
    ) {
        val view = viewHolder.itemView

        val txtUserItem = view.findViewById<TextView>(R.id.txtUser)
        txtUserItem.text = element

        txtUserItem.setOnClickListener {
            itemClickListener.onItemClicked(element!!)
        }
    }
}