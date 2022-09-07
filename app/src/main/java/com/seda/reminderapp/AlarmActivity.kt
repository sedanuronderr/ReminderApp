package com.seda.reminderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.text.SimpleDateFormat
import java.util.*
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.seda.reminderapp.RecyclerView.recyclerviewalarm
import com.seda.reminderapp.Worker.ReminderWorker
import com.seda.reminderapp.databinding.ActivityAlarmBinding
import com.seda.reminderapp.model.saat
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class AlarmActivity : AppCompatActivity() {
  private var saatdata =0
    var chosenYear = 0
    var chosenMonth = 0
    var chosenDay = 0
    var chosenHour = 0
    var chosenMin = 0
    private lateinit var alarmAdapter: recyclerviewalarm
    private lateinit var binding: ActivityAlarmBinding
    private lateinit var saatlist: ArrayList<saat>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)


prepareRecycler()

        onAlarmLongClickListener()
    }


    private fun prepareRecycler() {

        saatlist = ArrayList<saat>()

        saatlist.add(0, saat(30,"dk"))
        saatlist.add(1, saat(60,"dk"))
        saatlist.add(2, saat(120,"dk"))
        saatlist.add(3, saat(150,"dk"))
        saatlist.add(4, saat(180,"dk"))
        saatlist.add(5, saat(210,"dk"))
     alarmAdapter = recyclerviewalarm(this,saatlist)
        binding.recyclerView.apply {
            layoutManager =  LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter =alarmAdapter
        }
    }
    private fun onAlarmLongClickListener() {
        alarmAdapter.onLongClickListener = {meal->

            Log.e("cer","${meal.saat}")
            saatdata =meal.saat
            Log.e("saat","$saatdata")
            timee(saatdata)

        }


    }

     fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
            "dd.MM.yyyy",
            Locale.getDefault())

        return format.format(date)
    }

  private  fun timee(saatt :Int){




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

            }
            // check for single digit hour hour and minute
            // and update TextView accordingly

            // then update the preview TextView
        val userSelectedDateTime = Calendar.getInstance()

        userSelectedDateTime.set(chosenYear,chosenMonth,chosenDay,chosenHour,chosenMin)


        val todayDateTime = Calendar.getInstance()
        Log.e("time","${todayDateTime.get(Calendar.HOUR)}")
        Log.e("time","${todayDateTime.get(Calendar.MINUTE)}")
        val delayInSeconds =(userSelectedDateTime.get(Calendar.MINUTE).toLong() + 3)
        createWorkRequest("merhaba", delayInSeconds)
        Log.e("delay","$saatt")

        }


     fun createWorkRequest(message: String,timeDelayInSeconds: Long  ) {
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