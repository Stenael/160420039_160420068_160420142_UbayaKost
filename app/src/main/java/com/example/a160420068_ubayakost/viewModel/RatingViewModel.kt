package com.example.a160420068_ubayakost.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420068_ubayakost.model.Kost
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RatingViewModel(application: Application):
    AndroidViewModel(application) {
    val kostsLD = MutableLiveData<ArrayList<Kost>>()
    val kostLoadingLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD  = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh(){
        loadingLD.value = true
        kostLoadingLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://stenael.000webhostapp.com/api/kost_rating.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object: TypeToken<List<Kost>>() { }.type
                val result = Gson().fromJson<ArrayList<Kost>>(it, sType)
                kostsLD.value = result
                loadingLD.value = false

                Log.d("showvolley", result.toString())
            },
            {
                Log.d("showvolley", it.toString())
                kostLoadingLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}