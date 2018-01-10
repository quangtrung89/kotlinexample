package com.trung.kotlinexample.model.loginResult

import com.google.gson.annotations.SerializedName

data class Data(val userInfo: UserInfo?,
                val organizationName: String = "",
                val encodedValue: String? = null,
                val authorizedToken: String = "",
                val currencySymbol: String? = null,
                val userFirstName: String = "",
                val preferedLanguage: String = "",
                val userId: String = "",
                val imageUrlPrefix: String? = null,
                @SerializedName("_refs")
                val Refs: String? = null,
                val organizationId: String = "",
                val userLastName: String = "",
                val simpleToken: String = "",
                val userTypeAbbr: String = "",
                val organization: Organization?,
                val userPreferences: String? = null,
                val expiryTime: String? = null,
                val id: String = "",
                val sysMessage: String? = null,
                val device: String = "",
                val currencyCode: String? = null,
                val username: String = "")