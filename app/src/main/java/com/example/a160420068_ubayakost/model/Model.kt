package com.example.a160420068_ubayakost.model

import com.google.gson.annotations.SerializedName

data class Kost(
    val id:String?,
    @SerializedName("name")
    var name:String?,
    @SerializedName("desc")
    var desc:String?,
    @SerializedName("location")
    var location:String?,
    @SerializedName("type")
    var type:String?,
    @SerializedName("address")
    var addresss:String?,
    @SerializedName("price")
    var price:String?,
    @SerializedName("rating")
    var rating:String?,
    @SerializedName("photo")
    var photo:String?,
    @SerializedName("review1")
    var rev1:String?,
    @SerializedName("review2")
    var rev2:String?,
    @SerializedName("fal1")
    var fal1:String?,
    @SerializedName("fal2")
    var fal2:String?,
    @SerializedName("fal3")
    var fal3:String?
)
data class Profile(
    val username:String?,
    val password:String?,
    val address:String?,
    val number:String?
)
