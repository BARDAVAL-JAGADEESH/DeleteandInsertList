package com.bardaval.deleteandinsertlist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Deleteinsert : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_delete_in_list_view)

        val titleInput = findViewById<EditText>(R.id.titleInput)
        val descriptionInput = findViewById<EditText>(R.id.descriptionInput)
        val addBtn = findViewById<Button>(R.id.addBtn)
        val listview = findViewById<ListView>(R.id.listview)

        // List to store items
        val list = ArrayList<DataModel>()

        // Adapter for ListView
        val adapter = BaseAdapter(this, list)
        listview.adapter = adapter

        // Handle Add button click
        addBtn.setOnClickListener {
            // Ensure both inputs are filled
            if (titleInput.text.toString().isEmpty() || descriptionInput.text.toString().isEmpty()) {
                showToast("Please enter both title and description")
            } else {
                // Add new item to list
                list.add(DataModel(titleInput.text.toString(), descriptionInput.text.toString()))
                adapter.notifyDataSetChanged()
                titleInput.setText("")
                descriptionInput.setText("")
                showToast("Item Added")
            }
        }

        // Handle item click for deletion
        listview.setOnItemClickListener { _, _, position, _ ->
            list.removeAt(position)
            adapter.notifyDataSetChanged()
            showToast("Item Deleted")
        }
    }

    private var toast: Toast? = null
    private fun showToast(text: String) {
        toast?.cancel()
        toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
        toast?.show()
    }
}
