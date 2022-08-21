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

    private lateinit var binding: ActivityAlarmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)


tim()
showDateRangePicker()
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
        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))



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
        val  materialTimePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(10)
                .setTitleText("select time")
                .build()
        materialTimePicker.show(supportFragmentManager, "MainActivity")

        materialTimePicker.addOnPositiveButtonClickListener {

            val pickedHour: Int = materialTimePicker.hour
            val pickedMinute: Int = materialTimePicker.minute

            Log.e("saat",pickedHour.toString())
            // check for single digit hour hour and minute
            // and update TextView accordingly
            val formattedTime: String = when {
                pickedHour > 12 -> {
                    if (pickedMinute < 10) {
                        "${materialTimePicker.hour - 12}:0${materialTimePicker.minute} pm"
                    } else {
                        "${materialTimePicker.hour - 12}:${materialTimePicker.minute} pm"
                    }
                }
                pickedHour == 12 -> {
                    if (pickedMinute < 10) {
                        "${materialTimePicker.hour}:0${materialTimePicker.minute} pm"
                    } else {
                        "${materialTimePicker.hour}:${materialTimePicker.minute} pm"
                    }
                }
                pickedHour == 0 -> {
                    if (pickedMinute < 10) {
                        "${materialTimePicker.hour + 12}:0${materialTimePicker.minute} am"
                    } else {
                        "${materialTimePicker.hour + 12}:${materialTimePicker.minute} am"
                    }
                }
                else -> {
                    if (pickedMinute < 10) {
                        "${materialTimePicker.hour}:0${materialTimePicker.minute} am"
                    } else {
                        "${materialTimePicker.hour}:${materialTimePicker.minute} am"
                    }
                }
            }

            // then update the preview TextView
           binding.previewPickedTimeTextView.text = formattedTime

        }
    }

    private fun createWorkRequest(message: String,timeDelayInSeconds: Long  ) {
        val myWorkRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(timeDelayInSeconds, TimeUnit.MINUTES)
            .setInputData(
                workDataOf(
                "title" to "Sepetinde bir şey kaldı",
                "message" to message,
            )
            )
            .build()

        WorkManager.getInstance(this).enqueue(myWorkRequest)
    }
}