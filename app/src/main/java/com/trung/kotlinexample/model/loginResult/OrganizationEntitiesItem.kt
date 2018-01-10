package com.trung.kotlinexample.model.loginResult

import com.google.gson.annotations.SerializedName

data class OrganizationEntitiesItem(val gstRegistrationNumber: String? = null,
                                    val image: String? = null,
                                    val gstEnabled: Boolean = false,
                                    val address: Address?,
                                    val gstValue: String = "",
                                    val qboGstCodeList: String? = null,
                                    val description: String = "",
                                    val qboSalesItemId: String? = null,
                                    val accessToken: String? = null,
                                    val accessTokenUpdateDate: String? = null,
                                    val accessTokenSecret: String? = null,
                                    val connectedToQbo: Boolean = false,
                                    @SerializedName("_refs")
                                    val Refs: String? = null,
                                    val qboSalesItemList: String? = null,
                                    val entityName: String = "",
                                    val defaultEntity: Boolean = false,
                                    val gstLabel: String = "",
                                    val requestTokenSecret: String? = null,
                                    val smsSenderName: String? = null,
                                    val id: String = "",
                                    val priceIncludeGst: Boolean = false,
                                    val requestToken: String? = null,
                                    val gstTaxReferenceCode: String? = null,
                                    val qboid: String? = null)