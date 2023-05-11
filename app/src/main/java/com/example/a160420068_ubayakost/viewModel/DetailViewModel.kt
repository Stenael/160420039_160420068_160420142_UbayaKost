package com.example.a160420068_ubayakost.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420068_ubayakost.model.Kost
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel(application: Application):
    AndroidViewModel(application){
    val kostLD = MutableLiveData<Kost>()
    private var queue: RequestQueue? = null

    fun refresh(id : String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://stenael.000webhostapp.com/api/kost_list.php?id=$id"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<Kost>() { }.type
                val result = Gson().fromJson<Kost>(it, sType)
                kostLD.value = result

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
            }
        )
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
    }
}