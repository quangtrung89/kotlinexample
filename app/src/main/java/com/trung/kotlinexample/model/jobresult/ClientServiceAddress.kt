package com.trung.kotlinexample.model.jobresult

import com.google.gson.annotations.SerializedName

data class ClientServiceAddress(val country: String = "",
                                val createdUserId: String? = null,
                                val address2: String? = null,
                                val address1: String? = null,
                                val addressType: String? = null,
                                val latitude: Int = 0,
                                @SerializedName("_refs")
                                val Refs: String? = null,
                                val organizationId: String? = null,
                                val lastUpdatedUserId: String? = null,
                                val countryCode: String = "",
                                val pinCode: String = "",
                                val name: String = "",
                                val id: String = "",
                                val longitude: Int = 0)