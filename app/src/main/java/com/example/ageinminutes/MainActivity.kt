package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dateSelector_btn = findViewById(R.id.btnDatePicker) as Button
        dateSelector_btn.setOnClickListener(){view -> clickDatePicker(view)}
    }
    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "The Date Has Been Selected", Toast.LENGTH_SHORT).show()
            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

            //text view selected date
            val dateSelector_tv = findViewById(R.id.tvSelectedDate) as TextView
            dateSelector_tv.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            val ageInMinutes_tv = findViewById(R.id.tvAgeInMinutes) as TextView

            //to get the actual minutes, division to 60000 is needed
            //because the getTime function returns in milliseconds
            val selectedDateInMinutes = theDate!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time / 60000
            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

            ageInMinutes_tv.setText(differenceInMinutes.toString())}
            ,year
            ,month
            ,dayOfMonth).show()

    }

    // todo find what is getpack compose 
}