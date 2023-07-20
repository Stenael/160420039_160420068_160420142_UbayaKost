package com.example.a160420068_ubayakost.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface KostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg kost: Kost)

    @Query("SELECT * FROM kost")
    fun selectAllKost() :List<Kost>

    @Query("SELECT * FROM kost WHERE id= :id")
    fun selectKost(id:Int): Kost

    @Query("SELECT * FROM kost WHERE rating like '4%'")
    fun selectRatingKost(): List<Kost>
}