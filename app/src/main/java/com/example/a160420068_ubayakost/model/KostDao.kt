package com.example.a160420068_ubayakost.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface KostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg kost: Kost)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllHistory(vararg  historySewa: HistorySewa)

    @Query("SELECT * FROM kost")
    fun selectAllKost() :List<Kost>

    @Query("SELECT * FROM kost WHERE id= :id")
    fun selectKost(id:Int): Kost

    @Query("SELECT * FROM kost WHERE rating like '4%'")
    fun selectRatingKost(): List<Kost>

    @Query("UPDATE kost SET address=:address, price=:price, type=:type WHERE id=:id")
    suspend fun updateKost(address:String,price:String,type:String,id:Int)

    @Query("SELECT * FROM historysewa")
    fun selectAllHistory(): List<HistorySewa>
}