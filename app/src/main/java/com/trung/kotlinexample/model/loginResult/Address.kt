package com.trung.kotlinexample.model.loginResult

import com.google.gson.annotations.SerializedName

data class Address(val country: String = "",
                   val zipCode: String = "",
                   val createdUserId: String? = null,
                   val address1: String? = null,
                   val city: String = "",
                   val address2: String = "",
                   val mobileNumber: String? = null,
                   val nickName: String? = null,
                   val addressType: String? = null,
                   val latitude: Int = 0,
                   @SerializedName("_refs")
                   val Refs: String? = null,
                   val addressId: String? = null,
                   val lastUpdatedUserId: String? = null,
                   val firstName: String? = null,
                   val phoneNumber: String = "",
                   val preferredMode: String? = null,
                   val street: String = "",
                   val countryCode: String = "",
                   val district: String? = null,
                   val id: String = "",
                   val state: String? = null,
                   val email: String = "",
                   val lasttName: String? = null,
                   val longitude: Int = 0)