package com.trung.kotlinexample.utils

import com.trung.kotlinexample.network.RetrofitException
import java.text.SimpleDateFormat
import java.util.*


object AppUtils {
    fun getMessageErrorApi(throwable: Throwable): String? {
        val error = throwable as RetrofitException
        val response = error.response
        //        if (response != null && (response.code() == 400 || response.code() == 401)) {
        //            return context.getString(R.string.error_token_timeout);
        //        }

        val errorResponse = error.getError()
        if (errorResponse?.data != null) {
            return errorResponse.data
        } else if (response?.message() != null && response.message() != "") {
            return response.message()
        }
        return null
    }

    fun convertLongToDayOfWeek(time: Long): String {
        val weekDay: String
        val dayFormat = SimpleDateFormat("EEEE", Locale.US)
        weekDay = dayFormat.format(time)
        return weekDay
    }
    fun convertLongToDate(time: Long): String {
        val weekDay: String
        val dayFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)
        weekDay = dayFormat.format(time)
        return weekDay
    }
    fun convertTime(time: Long): String {
        val weekDay: String
        val dayFormat = SimpleDateFormat("hh:mm a", Locale.US)
        weekDay = dayFormat.format(time)
        return weekDay
    }
}
