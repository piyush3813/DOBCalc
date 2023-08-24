package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.Calendar
import java.util.Locale
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val datePickerButton: Button = findViewById(R.id.butonid)

        datePickerButton.setOnClickListener {
            datePicker()
        }
    }

    private fun datePicker(){
        var tvselecteddate: TextView? = findViewById(R.id.selecteddate)
        var tvAgeInminutes: TextView? = findViewById(R.id.inminutes)
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month  = calender.get(Calendar.MONTH)
        val date = calender.get(Calendar.DAY_OF_MONTH)
        val dpd =         DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view,Selectedyear,Selectedmonth,Selecteddate ->
                val selectedDate = "$Selecteddate/${Selectedmonth+1}/$Selectedyear"
                tvselecteddate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/YYYY", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMin = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val CurrentdateinMinutes = currentDate.time / 60000

                        val difference =  CurrentdateinMinutes - selectedDateInMin
                        tvAgeInminutes?.text = difference.toString()
                    }
                }
            },
            year,
            month,
            date)
dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
            dpd.show()



    }



}