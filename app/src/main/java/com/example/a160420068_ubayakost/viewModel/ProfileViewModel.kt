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
        val prof2 = Profile("Ardi","123","Sidoarjo","099999999")

        if(username == "Steven" && password == "123"){
            profileLD.value = prof1
        }
        else if (username == "Ardi" && password == "123"){
            profileLD.value = prof2
        }
    }

    fun checkLogin(username: String?, password: String?){
        launch {
            val db = buildDb(getApplication())
            profileLD.postValue(db.kostDao().checkProfile(username, password))
        }
    }

    fun refresh(username: String?){
        launch{
            val db = buildDb(getApplication())
            profileLD.postValue(db.kostDao().selectProfile(username))
        }
    }

    fun updateProfile(address: String?, number: String?, id:Int){
        launch {
            val db = buildDb(getApplication())
            db.kostDao().updateProfile(address,number,id)
        }
    }

    fun register(list:List<Profile>){
        launch {
            val db = buildDb(getApplication())
            db.kostDao().insertAllProfile(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}