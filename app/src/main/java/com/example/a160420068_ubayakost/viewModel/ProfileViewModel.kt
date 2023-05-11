package com.example.a160420068_ubayakost.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a160420068_ubayakost.model.Profile

class ProfileViewModel (application: Application):
    AndroidViewModel(application){
    val profileLD = MutableLiveData<Profile>()

    fun fetch(username:String?, password:String?){
        val prof1 = Profile("Steven","123","Surabaya","0812345678")
        val prof2 = Profile("Spn","345","Sidoarjo","099999999")

        if(username == "Steven" && password == "123"){
            profileLD.value = prof1
        }
        else if (username == "Spn" && password == "345"){
            profileLD.value = prof2
        }
    }
}