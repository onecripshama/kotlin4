package com.example.kotlin4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class DateListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dateAdapter: DateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_list)

        recyclerView = findViewById(R.id.dateRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dates = loadDatesFromFile()
        dateAdapter = DateAdapter(dates)
        recyclerView.adapter = dateAdapter
    }

    private fun loadDatesFromFile(): List<String> {
        val directory = getExternalFilesDir(null)
        val dates = mutableListOf<String>()

        directory?.listFiles()?.forEach { file ->
            if (file.isFile && file.name.endsWith(".jpg")) {
                val date = file.name.substringBeforeLast(".jpg")
                dates.add(date)
            }
        }

        dates.sort()
        Log.d("DateListActivity", "Loaded dates: $dates")
        return dates
    }
}