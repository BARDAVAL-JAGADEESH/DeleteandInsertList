package com.bardaval.deleteandinsertlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class BaseAdapter(var context: Context, var items: ArrayList<DataModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.listview, parent, false)

        val title = view.findViewById<TextView>(R.id.title)
        val description = view.findViewById<TextView>(R.id.description)
        val img = view.findViewById<ImageView>(R.id.img)
        val deleteIcon = view.findViewById<ImageView>(R.id.deleteIcon)

        title.text = items[position].title
        description.text = items[position].description
        img.setImageResource(R.drawable.adipurush)

        // Handle the delete operation
        deleteIcon.setOnClickListener {
            items.removeAt(position)
            notifyDataSetChanged()
            Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
