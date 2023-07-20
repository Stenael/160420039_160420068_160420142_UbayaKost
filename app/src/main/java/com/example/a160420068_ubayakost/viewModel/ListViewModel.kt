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
//import com.example.a160420068_ubayakost.model.HistorySewa
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
//    val historyLD = MutableLiveData<List<HistorySewa>>()
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
    }
//    fun fetch(){
//        launch {
//            val db= buildDb(
//                getApplication())
//            historyLD.postValue((db.kostDao().selectAllHistory()))
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}