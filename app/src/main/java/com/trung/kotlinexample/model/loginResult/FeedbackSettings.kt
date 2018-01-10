package com.trung.kotlinexample.model.loginResult

import com.google.gson.annotations.SerializedName

data class FeedbackSettings(val smsRequestTemplate: String = "",
                            val sendSMSRequest: Boolean = false,
                            val feedbackForm: FeedbackForm?,
                            val allowed: Boolean = false,
                            val id: String = "",
                            val sendEmailRequest: Boolean = false,
                            val enabled: Boolean = false,
                            val autoSendOnJobCompletion: AutoSendOnJobCompletion?,
                            @SerializedName("_refs")
                            val Refs: String? = null,
                            val smsRequestAllowed: Boolean = false)