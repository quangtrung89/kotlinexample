package com.trung.kotlinexample.model.loginResult

import com.google.gson.annotations.SerializedName

data class CustomMessageInEmails(val id: String = "",
                                 val enabled: Boolean = false,
                                 @SerializedName("_refs")
                                 val Refs: String? = null,
                                 val emailCustomMessage: String = "")