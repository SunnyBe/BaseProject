package com.zistus.core.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun todayDateIsoFormat(): String {
        val currentInMilli = Calendar.getInstance().time
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        return sdf.format(currentInMilli)
    }

    fun fetchDateTimeFromIso(isoDate: String = todayDateIsoFormat(), pattern: String): String? {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        val updatedString = isoDate.replace("\\+0([0-9]){1}\\:00", "+0$100")
        val extractedDate = inputDateFormat.parse(updatedString)
        val newFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return extractedDate?.let { newFormat.format(it) }
    }

    fun fetchMonthYear(isoDate: String? = todayDateIsoFormat()): String? {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        val updatedString = isoDate?.replace("\\+0([0-9]){1}\\:00", "+0$100")
        val extractedDate = inputDateFormat.parse(updatedString)
        val newFormat = SimpleDateFormat("MMM YYYY", Locale.getDefault())
        return extractedDate?.let { newFormat.format(it) }
    }

    fun fetchDay(date: String?): String? {
        return if (date == null) null else fetchDateTimeFromIso(date, "dd")
    }

    fun fetchTime(date: String?): String? {
        return if (date == null) null else fetchDateTimeFromIso(date, "hh:mma")
    }
}