package com.example.a160420068_ubayakost.model

import com.google.gson.annotations.SerializedName

data class Kost(
    val id:String?,
    @SerializedName("name")
    val name:String?,
    @SerializedName("desc")
    val desc:String?,
    @SerializedName("location")
    val location:String?,
    @SerializedName("type")
    val type:String?,
    @SerializedName("address")
    val addresss:String?,
    @SerializedName("price")
    val price:String?,
    @SerializedName("rating")
    val rating:String?,
    @SerializedName("photo")
    val photo:String,
    @SerializedName("review1")
    val rev1:String?,
    @SerializedName("review2")
    val rev2:String?,
    @SerializedName("fal1")
    val fal1:String?,
    @SerializedName("fal2")
    val fal2:String?,
    @SerializedName("fal3")
    val fal3:String?
)
data class Profile(
    val username:String?,
    val password:String?,
    val address:String?,
    val number:String?
)
