package com.example.a160420068_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420068_ubayakost.Global
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.databinding.KostListItemBinding
import com.example.a160420068_ubayakost.model.Kost
import com.example.a160420068_ubayakost.util.loadImage

class KostListAdapter(val kostList:ArrayList<Kost>)
    :RecyclerView.Adapter<KostListAdapter.KostViewHolder>(),ButtonDetailClickListener{
    class KostViewHolder(var view: KostListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<KostListItemBinding>(inflater,R.layout.kost_list_item, parent, false)
        return KostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return kostList.size
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        holder.view.kost = kostList[position]
        holder.view.listener = this
    }
    fun updateKostList(newKostList: ArrayList<Kost>) {
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = KostListFragmentDirections.actionToDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}
