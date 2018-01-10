package com.trung.kotlinexample.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View

import com.trung.kotlinexample.model.jobresult.JobItem
import com.trung.kotlinexample.utils.AppUtils


class ItemJobViewModel(jobItem: JobItem?) : BaseObservable() {
    var dayOfWeekRequest: ObservableField<String>? = null
    var dateRequest: ObservableField<String>? = null
    var startEnd: ObservableField<String>? = null
    var serviceName: ObservableField<String>? = null
    var clientName: ObservableField<String>? = null
    var requestStatus: ObservableField<String>? = null
    var isVisiableHeader: ObservableInt? = null
    var isVisiableItem: ObservableInt? = null

    init {
        jobItem?.let {
            isVisiableHeader = if (it.type_item == JobItem.TYPE_HEADER) {
                ObservableInt(View.VISIBLE)
            } else {
                ObservableInt(View.GONE)
            }
            isVisiableItem = if (it.type_item == JobItem.TYPE_HEADER) {
                ObservableInt(View.GONE)
            } else {
                ObservableInt(View.VISIBLE)
            }
            startEnd = if (it.startDate != 0L && it.endDate != 0L) {
                ObservableField(
                        AppUtils.convertTime(it.startDate) + " - " + AppUtils.convertTime(it.endDate))
            } else {
                ObservableField("N/A")
            }
            if (it.requestDate != 0L) {
                dayOfWeekRequest = ObservableField(AppUtils.convertLongToDayOfWeek(it.requestDate))
                dateRequest = ObservableField(AppUtils.convertLongToDate(it.requestDate))
            }
            serviceName = if (it.serviceName.isEmpty()) {
                ObservableField("N/A")
            } else {
                ObservableField(it.serviceName)
            }
            clientName = if (it.clientName.isEmpty()) {
                ObservableField("N/A")
            } else {
                ObservableField(it.clientName)
            }
            requestStatus = if (it.requestStatus.isEmpty()) {
                ObservableField("N/A")
            } else {
                ObservableField(it.requestStatus)
            }
        }
    }
}
