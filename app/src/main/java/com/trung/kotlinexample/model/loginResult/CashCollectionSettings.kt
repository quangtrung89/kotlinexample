package com.trung.kotlinexample.model.loginResult

import com.google.gson.annotations.SerializedName

data class CashCollectionSettings(val allowed: Boolean = false,
                                  val id: String = "",
                                  val enabledForMobile: Boolean = false,
                                  val enabled: Boolean = false,
                                  @SerializedName("_refs")
                                  val Refs: String? = null)