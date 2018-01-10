package com.trung.kotlinexample.model.loginResult

import com.google.gson.annotations.SerializedName

data class UnassignedContractJobReminderSettings(val allowed: Boolean = false,
                                                 val smsTemplate: String = "",
                                                 val id: String? = null,
                                                 val earlyReminderDays: Int = 0,
                                                 val enabled: Boolean = false,
                                                 @SerializedName("_refs")
                                                 val Refs: String? = null,
                                                 val sendTime: String = "")