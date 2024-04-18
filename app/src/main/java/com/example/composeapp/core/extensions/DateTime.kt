package com.example.composeapp.core.extensions

import android.annotation.SuppressLint
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
fun String.toDayName(): String {
    val formatter = java.text.SimpleDateFormat("yyyy-MM-dd")
    val date = formatter.parse(this) ?: return "Unknown"
    val today = Calendar.getInstance().time

    return if (date.date == today.date &&
        date.month == today.month &&
        date.year == today.year
    ) {
        "Today"
    } else {
        formatter.applyPattern("EEEE") // Apply pattern for full weekday name
        formatter.format(date)
    }
}
