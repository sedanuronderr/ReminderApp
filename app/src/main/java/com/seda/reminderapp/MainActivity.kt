package com.seda.reminderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.seda.reminderapp.Worker.ReminderWorker
import com.seda.reminderapp.databinding.ActivityMainBinding
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        var chosenYear = 0
        var chosenMonth = 0
        var chosenDay = 0
        var chosenHour = 0
        var chosenMin = 0

        val today = Calendar.getInstance()
/*
      binding.datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) {_, year, month, day ->
          chosenYear = year
          chosenMonth = month
          chosenDay = day}

binding.timePicker.setOnTimeChangedListener{_, hour, minute ->
    chosenHour = hour
    chosenMin  = minute
    var am_pm = ""
    // AM_PM decider logic
    when {hour == 0 -> { chosenHour += 12
        am_pm = "AM"
    }
        hour == 12 -> am_pm = "PM"
        hour > 12 -> { chosenHour -= 12
            am_pm = "PM"
        }
        else -> am_pm = "AM"
    }
    Log.e("time is","$chosenHour : $minute $am_pm")

}*/

    /*    binding.setBtn.setOnClickListener {
            val userSelectedDateTime = Calendar.getInstance()

            userSelectedDateTime.set(chosenYear,chosenMonth,chosenDay,chosenHour,chosenMin)


            val todayDateTime = Calendar.getInstance()
            Log.e("time","${todayDateTime.get(Calendar.HOUR)}")
            Log.e("time","${todayDateTime.get(Calendar.MINUTE)}")
            val delayInSeconds =(userSelectedDateTime.get(Calendar.MINUTE))  - (todayDateTime.get(Calendar.MINUTE).toLong())
            createWorkRequest(binding.editText.text.toString(), delayInSeconds)
Log.e("delay","$delayInSeconds")
            // 10
            Toast.makeText(this, "Reminder set", Toast.LENGTH_SHORT).show()

        }

*/
    }

    private fun createWorkRequest(message: String,timeDelayInSeconds: Long  ) {
        val myWorkRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(timeDelayInSeconds, TimeUnit.MINUTES)
            .setInputData(workDataOf(
                "title" to "Sepetinde bir şey kaldı",
                "message" to message,
            )
            )
            .build()

        WorkManager.getInstance(this).enqueue(myWorkRequest)
    }
}