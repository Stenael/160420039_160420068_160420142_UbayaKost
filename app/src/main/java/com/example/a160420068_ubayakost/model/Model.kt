package com.example.a160420068_ubayakost.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "kost")
data class Kost(
    @ColumnInfo("name")
    var name:String?,
    @ColumnInfo("desc")
    var desc:String?,
    @ColumnInfo("location")
    var location:String?,
    @ColumnInfo("type")
    var type:String?,
    @ColumnInfo("address")
    var addresss:String?,
    @ColumnInfo("price")
    var price:String?,
    @ColumnInfo("rating")
    var rating:String?,
    @ColumnInfo("photo")
    var photo:String?,
    @ColumnInfo("review1")
    var rev1:String?,
    @ColumnInfo("review2")
    var rev2:String?,
    @ColumnInfo("fal1")
    var fal1:String?,
    @ColumnInfo("fal2")
    var fal2:String?,
    @ColumnInfo("fal3")
    var fal3:String?
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
@Entity(tableName = "profile")
data class Profile(
    @ColumnInfo("username")
    val username:String?,
    @ColumnInfo("password")
    val password:String?,
    @ColumnInfo("address")
    val address:String?,
    @ColumnInfo("number")
    val number:String?
)

//@Entity(tableName = "historysewa")
//data class HistorySewa(
//    @ColumnInfo("kostname")
//    val kostname:String?,
//    @ColumnInfo("tanggalselesai")
//    val tanggalselesai:String?
//){
//    @PrimaryKey(autoGenerate = true)
//    var id:Int = 0
//}
