package com.trung.kotlinexample.model.loginResult

import com.google.gson.annotations.SerializedName

data class AutoSendEmail(val emailTimeFormat: String = "",
                         val customMessageInEmails: CustomMessageInEmails?,
                         val autoSendServiceInvoice: AutoSendServiceInvoice?,
                         val autoSendServiceReport: AutoSendServiceReport?,
                         val id: String = "",
                         val autoSendEmailOnJobAssignment: Boolean = false,
                         val enabled: Boolean = false,
                         val autoSendEmailOnJobReschedule: Boolean = false,
                         @SerializedName("_refs")
                         val Refs: String? = null)