package com.example.a160420068_ubayakost.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a160420068_ubayakost.util.MIGRATION_1_2
import com.example.a160420068_ubayakost.util.MIGRATION_2_3
import com.example.a160420068_ubayakost.util.MIGRATION_3_4
import com.example.a160420068_ubayakost.util.MIGRATION_4_5

@Database(entities = [Kost::class,Profile::class],version = 6)
abstract class KostDatabase:RoomDatabase() {
    abstract fun kostDao() : KostDao

    companion object{
        @Volatile private var instance: KostDatabase ?= null
        private val LOCK = Any()
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                KostDatabase::class.java, "kostdbnew")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5)
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