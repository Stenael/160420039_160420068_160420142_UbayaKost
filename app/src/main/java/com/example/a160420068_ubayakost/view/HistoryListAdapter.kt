package com.example.a160420068_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.databinding.HistorysewaListItemBinding
import com.example.a160420068_ubayakost.databinding.KostListItemBinding
import com.example.a160420068_ubayakost.model.HistorySewa
import org.w3c.dom.Text

class HistoryListAdapter(val historyList: ArrayList<HistorySewa>) : RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>(){

    class HistoryViewHolder(var view: HistorysewaListItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HistorysewaListItemBinding.inflate(inflater, parent, false)

        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.view.history = historyList[position]
    //        var txtKostName = holder.view.findViewById<TextView>(R.id.txtKostName)
//        var txtDate = holder.view.findViewById<TextView>(R.id.txtDate)
//
//        txtKostName.text = historyList[position].kostname
//        txtDate.text = historyList[position].tanggalselesai
    }

    fun updateHistoryList(newHistoryList: List<HistorySewa>){
        historyList.clear()
        historyList.addAll(newHistoryList)
        notifyDataSetChanged()
    }
}