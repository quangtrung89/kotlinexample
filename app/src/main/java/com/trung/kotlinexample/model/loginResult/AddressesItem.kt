package com.trung.kotlinexample.model.loginResult

import com.google.gson.annotations.SerializedName

data class AddressesItem(val country: String = "",
                         val zipCode: String = "",
                         val createdUserId: String? = null,
                         val address2: String? = null,
                         val city: String? = null,
                         val address1: String? = null,
                         val mobileNumber: String? = null,
                         val nickName: String? = null,
                         val addressType: String = "",
                         val latitude: Int = 0,
                         @SerializedName("_refs")
                         val Refs: String? = null,
                         val addressId: String? = null,
                         val lastUpdatedUserId: String = "",
                         val firstName: String = "",
                         val phoneNumber: String = "",
                         val preferredMode: String? = null,
                         val street: String? = null,
                         val countryCode: String = "",
                         val district: String? = null,
                         val id: String = "",
                         val state: String? = null,
                         val email: String = "",
                         val lasttName: String = "",
                         val longitude: Int = 0)