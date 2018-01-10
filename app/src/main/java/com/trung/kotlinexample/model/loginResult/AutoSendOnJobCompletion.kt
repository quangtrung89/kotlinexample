package com.trung.kotlinexample.model.loginResult

import com.google.gson.annotations.SerializedName

data class AutoSendOnJobCompletion(val onWeb: Boolean = false,
                                   val id: String = "",
                                   val enabled: Boolean = false,
                                   val onMobile: Boolean = false,
                                   val onAdHocJobs: Boolean = false,
                                   @SerializedName("_refs")
                                   val Refs: String? = null,
                                   val onContractJobs: Boolean = false)