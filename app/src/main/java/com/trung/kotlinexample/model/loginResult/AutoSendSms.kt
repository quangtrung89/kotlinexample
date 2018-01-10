package com.trung.kotlinexample.model.loginResult

import com.google.gson.annotations.SerializedName

data class AutoSendSms(val assignmentTemplate: String? = null,
                       val autoSendSmsOnJobReschedule: Boolean = false,
                       val rescheduleTemplateSignature: String? = null,
                       val rescheduleTemplate: String? = null,
                       val enableAssignSmsTemplate: Boolean = false,
                       val id: String = "",
                       val enableRescheduleSmsTemplate: Boolean = false,
                       val smsTimeFormat: String = "",
                       val enabled: Boolean = false,
                       val autoSendSmsOnJobAssignment: Boolean = false,
                       @SerializedName("_refs")
                       val Refs: String? = null,
                       val assignTemplateSignature: String? = null)