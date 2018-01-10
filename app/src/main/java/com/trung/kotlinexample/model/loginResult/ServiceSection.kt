package com.trung.kotlinexample.model.loginResult

import com.google.gson.annotations.SerializedName

data class ServiceSection(val description: String = "",
                          val id: String = "",
                          val title: String = "",
                          val enabled: Boolean = false,
                          @SerializedName("_refs")
                          val Refs: String? = null)