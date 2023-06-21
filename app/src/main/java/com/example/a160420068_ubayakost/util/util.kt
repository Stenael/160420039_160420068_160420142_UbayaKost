package com.example.a160420068_ubayakost.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.example.a160420068_ubayakost.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

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
fun loadPhotoURL(view:ImageView,url:String, pb:ProgressBar){
    view.loadImage(url,pb)
}
@BindingAdapter("android:imageURL")
fun loadPhotoUrl(view:ImageView,url:String) {
    view.loadImageURL(url)
}