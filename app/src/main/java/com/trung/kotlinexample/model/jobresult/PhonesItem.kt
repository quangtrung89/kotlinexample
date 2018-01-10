package com.trung.kotlinexample.model.jobresult

import com.google.gson.annotations.SerializedName

data class PhonesItem(val number: String = "",
                      val id: String = "",
                      val type: String = "",
                      @SerializedName("_refs")
                      val Refs: String? = null)