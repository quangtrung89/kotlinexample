package com.trung.kotlinexample.model.jobresult

import com.google.gson.annotations.SerializedName

data class PhoneNumbersItem(val name: String = "",
                            val phones: List<PhonesItem>?,
                            val id: String = "",
                            @SerializedName("_refs")
                            val Refs: String? = null)