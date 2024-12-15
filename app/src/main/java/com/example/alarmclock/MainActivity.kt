package com.example.alarmclock

import android.app.AlarmManager
import android.app.AlarmManager.RTC_WAKEUP
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var materialTimePicker: MaterialTimePicker? = null
    private var calendar: Calendar? = null
    private lateinit var toolbar: Toolbar
private lateinit var timeTV:TextView
    private lateinit var allarmButtonBTN: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
timeTV = findViewById(R.id.timeTV)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        allarmButtonBTN = findViewById(R.id.allarmButtonBTN)
        allarmButtonBTN.setOnClickListener {
            materialTimePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("выберите время будильника")
                .build()
            materialTimePicker!!.addOnPositiveButtonClickListener {
                calendar = Calendar.getInstance()
                calendar?.set(Calendar.SECOND, 0)
                calendar?.set(Calendar.MILLISECOND, 0)
                calendar?.set(Calendar.MINUTE, materialTimePicker!!.minute)
                calendar?.set(Calendar.HOUR_OF_DAY, materialTimePicker!!.hour)
                val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
                alarmManager.setExact(
                    RTC_WAKEUP,
                    calendar?.timeInMillis!!,
                    getAlarmPendingIntent()!!

                )
                timeTV.text = "Будильник установлен на: ${dateFormat.format(calendar!!.time)}"

            }

            materialTimePicker!!.show(supportFragmentManager, "tag_picker")
        }

    }

    private fun getAlarmPendingIntent(): PendingIntent? {
        val intent = Intent(this, AlarmReceiver::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
return PendingIntent.getBroadcast(
    this,
    1,
    intent,
    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
)
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
