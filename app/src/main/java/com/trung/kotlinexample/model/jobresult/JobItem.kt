package com.trung.kotlinexample.model.jobresult

import com.google.gson.annotations.SerializedName

data class JobItem(val serviceRequestType: String = "",
                   val clientServiceAddress: ClientServiceAddress?,
                   val clientId: String = "",
                   val taskDetailRequestId: String? = null,
                   val clientName: String = "",
                   val clientEmail: String? = null,
                   val endDate: Long = 0,
                   val imageLogoHeight: Int = 0,
                   val imageLogoWidth: Int = 0,
                   val serviceName: String = "",
                   val phoneNumbers: List<PhoneNumbersItem>?,
                   val fromSubclient: Boolean = false,
                   @SerializedName("imageBase64")
                   val imageBase: String? = null,
                   val requestId: String = "",
                   val serviceRequest: String? = null,
                   val contractPayments: String? = null,
                   val requestDate: Long = 0,
                   val userRegId: String = "",
                   val userTaskDetailsId: String = "",
                   val startDate: Long = 0,
                   val requestStatus: String = "",
                   var type_item: Int = TYPE_JOB) {
    companion object {
        val TYPE_HEADER: Int = 1
        val TYPE_JOB: Int = 2
    }
}