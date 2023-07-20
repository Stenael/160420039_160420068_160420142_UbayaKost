package com.example.a160420068_ubayakost.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a160420068_ubayakost.util.*

@Database(entities = [Kost::class,HistorySewa::class,Profile::class],version = 7)
abstract class KostDatabase:RoomDatabase() {
    abstract fun kostDao() : KostDao

    companion object{
        @Volatile private var instance: KostDatabase ?= null
        private val LOCK = Any()
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                KostDatabase::class.java, "kostdbnew")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_7)
                .createFromAsset("kostdbnew")
                .fallbackToDestructiveMigration()
                .build()
        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }


}