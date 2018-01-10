package com.trung.kotlinexample.model.loginResult

import com.google.gson.annotations.SerializedName

data class DeviceDetail(val deviceType: String = "",
                        val id: String = "",
                        val version: String = "",
                        val platform: String = "",
                        @SerializedName("_refs")
                        val Refs: String? = null,
                        val deviceToken: String = "")