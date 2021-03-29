package com.example.demo.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Client (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("fullName") val fullName: String,
    @SerializedName("pic") val picture: String,

    @SerializedName("formated_joindate") val joinDate: String,

    @SerializedName("email") val email: String,
    @SerializedName("tel") val telephone: String,
    @SerializedName("mobile") val mobile: String,
    @SerializedName("fax") val fax: String,

    @SerializedName("delivaddr_part1") val deliveryAddr1: String?,
    @SerializedName("delivaddr_part2") val deliveryAddr2: String?,
    @SerializedName("delivaddr_zip") val deliveryZip: String?,
    @SerializedName("delivaddr_town") val deliveryTown: String?,
    @SerializedName("delivaddr_state") val deliveryState: String?,
    @SerializedName("delivaddr_countrycode") val deliveryCountry: String?,

    @SerializedName("billing_part1") val billingAddr1: String?,
    @SerializedName("billing_part2") val billingAddr2: String?,
    @SerializedName("billing_zip") val billingZip: String?,
    @SerializedName("billing_town") val billingTown: String?,
    @SerializedName("billing_state") val billingState: String?,
    @SerializedName("billing_countrycode") val billingCountry: String?,

    @SerializedName("auxCode") val auxCode: String,
    @SerializedName("ident") val reference: String
): Parcelable