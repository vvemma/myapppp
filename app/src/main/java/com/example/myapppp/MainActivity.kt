package com.example.myapppp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchButton: Button = findViewById(R.id.button_search)

        searchButton.setOnClickListener {
            clickEvent()
        }
    }

    private fun clickEvent() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }
}
