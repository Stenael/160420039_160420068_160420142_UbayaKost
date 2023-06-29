package com.example.a160420068_ubayakost.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420068_ubayakost.model.Kost
import com.example.a160420068_ubayakost.model.KostDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application):
    AndroidViewModel(application), CoroutineScope{
    private val job = Job()
    val kostLD = MutableLiveData<Kost>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun addTodo(list:List<Kost>){
        launch {
            val db = Room.databaseBuilder(
                getApplication(), KostDatabase::class.java,
                "newkostdb").build()
            db.kostDao().insertAll(*list.toTypedArray())
        }
    }

    fun refresh(id : String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://stenael.000webhostapp.com/api/kost_list.php?id=$id"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<Kost>(it, Kost::class.java)
                kostLD.value = result

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}