package com.example.a160420068_ubayakost.view

import android.view.View
import com.example.a160420068_ubayakost.model.Kost
import com.example.a160420068_ubayakost.model.Profile

interface ButtonDetailClickListener {
    fun onButtonDetailClick(v: View)
}
interface ButtonEditClickListener{
    fun onButtonEditClick(v:View)
}
interface ButtonReviewClickListener{
    fun onButtonReviewClick(v:View)
}
interface ButtonBookingClickListener{
    fun onButtonBookingClick(v:View)
}
interface ButtonEditKostClickListener{
    fun onButtonEditKostClick(v:View, kost: Kost)
}
interface ButtonEditProfileClickListener{
    fun onButtonEditProfileClick(v:View, profile: Profile)
}