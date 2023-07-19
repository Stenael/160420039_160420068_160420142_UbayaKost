package com.example.a160420068_ubayakost.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420068_ubayakost.model.Kost
import com.example.a160420068_ubayakost.model.KostDatabase
import com.example.a160420068_ubayakost.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application):
    AndroidViewModel(application), CoroutineScope{
    val kostsLD = MutableLiveData<List<Kost>>()
    val kostLoadingLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD  = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var job = Job()
    private var queue:RequestQueue? = null

    fun refresh(){
        loadingLD.value = true
        kostLoadingLoadErrorLD.value = false
        launch {
            val db = buildDb(
                getApplication())
            kostsLD.postValue(db.kostDao().selectAllKost())
        }

//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://stenael.000webhostapp.com/api/kost_list.php"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object: TypeToken<List<Kost>>() { }.type
//                val result = Gson().fromJson<ArrayList<Kost>>(it, sType)
//                kostsLD.value = result
//                loadingLD.value = false
//
//                Log.d("showvolley", result.toString())
//            },
//            {
//                Log.d("showvolley", it.toString())
//                kostLoadingLoadErrorLD.value = false
//                loadingLD.value = false
//            })
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}