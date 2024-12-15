package com.example.alarmclock

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class AlarmActivity : AppCompatActivity() {
    private lateinit var allarmStopButtonBTN: Button
    private lateinit var toolbarTwo: Toolbar
    private lateinit var imageView:ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_alarm)
        imageView = findViewById(R.id.imageView)
        toolbarTwo = findViewById(R.id.toolbarTwo)
        setSupportActionBar(toolbarTwo)
        allarmStopButtonBTN = findViewById(R.id.allarmStopButtonBTN)
        allarmStopButtonBTN.setOnClickListener {
            finish()
exitProcess(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_exit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_exit -> finishAffinity()
        }

        Toast.makeText(applicationContext, "Работа завершена", Toast.LENGTH_LONG).show()

        return super.onOptionsItemSelected(item)
    }
}