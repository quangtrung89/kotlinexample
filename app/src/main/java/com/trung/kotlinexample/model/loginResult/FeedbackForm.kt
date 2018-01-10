package com.trung.kotlinexample.model.loginResult

import com.google.gson.annotations.SerializedName

data class FeedbackForm(val serviceProviderSection: ServiceProviderSection?,
                        val id: String = "",
                        val starRatingEnabled: Boolean = false,
                        val serviceSection: ServiceSection?,
                        @SerializedName("_refs")
                        val Refs: String? = null)