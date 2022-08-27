package com.seda.reminderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.text.SimpleDateFormat
import java.util.*
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.seda.reminderapp.Worker.ReminderWorker
import com.seda.reminderapp.databinding.ActivityAlarmBinding
import java.util.concurrent.TimeUnit

class AlarmActivity : AppCompatActivity() {

    var chosenYear = 0
    var chosenMonth = 0
    var chosenDay = 0
    var chosenHour = 0
    var chosenMin = 0
    private lateinit var binding: ActivityAlarmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)


tim()

    }
    private fun showDateRangePicker(){
        val dateRangePicker =
            MaterialDatePicker
                .Builder.dateRangePicker()
                .setTitleText("Select Date")

                .build()

        dateRangePicker.show(
            supportFragmentManager,
            "date_range_picker"
        )
        dateRangePicker.addOnPositiveButtonClickListener { datePicked->
            val startDate = datePicked.first
            val endDate = datePicked.second


            if(startDate != null && endDate != null){
             binding.tvRangeDate.text =
                 "Reserved\nStartDate: ${convertLongToTime(startDate)}\n" +
                         "EndDate: ${convertLongToTime(endDate)}"

            }

        }




    }
    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
            "dd.MM.yyyy",
            Locale.getDefault())

        return format.format(date)
    }

    fun tim(){
        val today = Calendar.getInstance()



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

            }
            // check for single digit hour hour and minute
            // and update TextView accordingly

            // then update the preview TextView
        val userSelectedDateTime = Calendar.getInstance()

        userSelectedDateTime.set(chosenYear,chosenMonth,chosenDay,chosenHour,chosenMin)


        val todayDateTime = Calendar.getInstance()
        Log.e("time","${todayDateTime.get(Calendar.HOUR)}")
        Log.e("time","${todayDateTime.get(Calendar.MINUTE)}")
        val delayInSeconds =(userSelectedDateTime.get(Calendar.MINUTE).toLong())
        createWorkRequest("merhaba", delayInSeconds)
        Log.e("delay","$delayInSeconds")

        }


    private fun createWorkRequest(message: String,timeDelayInSeconds: Long  ) {
        val myWorkRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(timeDelayInSeconds, TimeUnit.MINUTES)
            .setInputData(
                workDataOf(
                "title" to "Drink Water Time",
                "message" to message,
            )
            )
            .build()

        WorkManager.getInstance(this).enqueue(myWorkRequest)
    }
}