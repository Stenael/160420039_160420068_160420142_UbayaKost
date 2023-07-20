package com.example.a160420068_ubayakost.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import com.example.a160420068_ubayakost.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a160420068_ubayakost.model.KostDatabase

val DB_NAME = "kostdbnew"

fun buildDb(context: Context): KostDatabase {
    val db = Room.databaseBuilder(context, KostDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_7)
        .build()
    return db
}
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "INSERT INTO kost(name,desc,location,type,address,price,rating,photo,review1,review2,fal1,fal2,fal3) " +
                    "VALUES('tes','tes','tes','tes','tes','1','1'" +
                    ",'https://static.mamikos.com/uploads/cache/data/style/2022-07-11/nXFXf2d6-540x720.jpg'," +
                    "'tes','tes','tes','tes','tes'")
    }
}
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "INSERT INTO kost(name,desc,location,type,address,price,rating,photo,review1,review2,fal1,fal2,fal3) " +
                    "VALUES('tes','tes','tes','tes','tes','1','1'" +
                    ",'https://static.mamikos.com/uploads/cache/data/style/2022-07-11/nXFXf2d6-540x720.jpg'," +
                    "'tes','tes','tes','tes','tes')")
    }
}
val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "INSERT INTO kost(name,desc,location,type,address,price,rating,photo,review1,review2,fal1,fal2,fal3) " +
                    "VALUES('tes','tes','tes','tes','tes','1','1','https://static.mamikos.com/uploads/cache/data/style/2022-07-11/nXFXf2d6-540x720.jpg'," +
                    "'tes','tes','tes','tes','tes')")
    }
}
val MIGRATION_4_5 = object : Migration(4, 5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "INSERT INTO kost(name,desc,location,type,address,price,rating,photo,review1,review2,fal1,fal2,fal3) " +
                    "VALUES('tes','tes','tes','tes','tes','1','1','https://static.mamikos.com/uploads/cache/data/style/2022-07-11/nXFXf2d6-540x720.jpg'," +
                    "'tes','tes','tes','tes','tes')")
    }
}

val MIGRATION_5_7 = object : Migration(5, 7) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE kost ADD COLUMN isBooked INTEGER DEFAULT 0")
        database.execSQL(
            "CREATE TABLE `profile` (`username` TEXT, `password` TEXT, `address` TEXT, `number` TEXT, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL);")
    }
}



fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this,object: Callback {
            override fun onSuccess(){
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?){
            }
        })
}
fun ImageView.loadImageURL(url: String?) {
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this,object: Callback {
            override fun onSuccess(){
            }

            override fun onError(e: Exception?){
            }
        })
}
@BindingAdapter("android:imageUrl","android:progressBar")
fun loadPhotoURL(view:ImageView,url:String?, pb:ProgressBar){
    view.loadImage(url,pb)
}
@BindingAdapter("android:imageURL")
fun loadPhotoUrl(view:ImageView,url:String) {
    view.loadImageURL(url)
}