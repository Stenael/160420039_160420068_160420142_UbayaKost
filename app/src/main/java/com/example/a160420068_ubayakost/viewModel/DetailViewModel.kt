package com.example.a160420068_ubayakost.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.example.a160420068_ubayakost.model.Kost
import com.example.a160420068_ubayakost.util.buildDb
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

    fun refresh(id : Int){
        launch{
            val db = buildDb(getApplication())
            kostLD.postValue(db.kostDao().selectKost(id))
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun updateKost(address: String?, price: String?, type: String?, id:Int){
        launch {
            val db = buildDb(getApplication())
            db.kostDao().updateKost(address,price,type,id)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}