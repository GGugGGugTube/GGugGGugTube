package com.example.myapplication

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {

    fun getDateFromTimestampWithFormat(
        timestamp: String?,
        fromFormatformat: String?,
        toFormatformat: String?
    ): String {
        var date: Date? = null
        var res = ""
        try {
            val format = SimpleDateFormat(fromFormatformat)
            date = format.parse(timestamp)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        Log.d("jbdate", "getDateFromTimestampWithFormat date >> $date")

        val df = SimpleDateFormat(toFormatformat)
        res = df.format(date)
        return res
    }
}
