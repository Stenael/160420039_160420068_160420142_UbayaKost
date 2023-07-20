package com.example.a160420068_ubayakost.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a160420068_ubayakost.model.Profile
import com.example.a160420068_ubayakost.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel (application: Application):
    AndroidViewModel(application), CoroutineScope{
    private val job = Job()
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

    fun refresh(id : Int){
        launch{
            val db = buildDb(getApplication())
            profileLD.postValue(db.kostDao().selectProfile(id))
        }
    }

    fun updateProfile(address: String?, number: String?, id:Int){
        launch {
            val db = buildDb(getApplication())
            db.kostDao().updateProfile(address,number,id)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}