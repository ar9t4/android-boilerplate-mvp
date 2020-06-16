package com.android.boilerplate.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    private const val DATE_TIME_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss"

    fun convertSecToMinAndSec(seconds: Long): String {
        val min = (seconds % 3600) / 60
        val sec = seconds % 60
        return String.format("%02d:%02d", min, sec)
    }

    private fun convertUtcTimeToMillis(utcDate: String): Long? {
        val dateFormat = SimpleDateFormat(DATE_TIME_FORMAT_UTC, Locale.ENGLISH)
        val date = dateFormat.parse(utcDate)
        val timeZoneId = Calendar.getInstance().timeZone.id
        val offset = TimeZone.getTimeZone(timeZoneId).getOffset(date!!.time)
        val localDate = Date(date.time.plus(offset))
        return localDate.time
    }

    fun generateReadableTimeStamp(utcDate: String): String {
        convertUtcTimeToMillis(utcDate)?.let {
            return DateUtils.getRelativeTimeSpanString(
                it,
                System.currentTimeMillis(),
                0
            ).toString()
        }
        return utcDate
    }
}