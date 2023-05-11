package com.example.a160420068_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420068_ubayakost.Global
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.model.Kost
import com.example.a160420068_ubayakost.util.loadImage

class KostListAdapter(val kostList:ArrayList<Kost>)
    :RecyclerView.Adapter<KostListAdapter.KostViewHolder>(){
    class KostViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kost_list_item, parent, false)
        return KostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return kostList.size
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        val txtName = holder.view.findViewById<TextView>(R.id.txtName)
        val txtRating = holder.view.findViewById<TextView>(R.id.txtRating)
        val txtPrice = holder.view.findViewById<TextView>(R.id.txtPrice)
        val txtType = holder.view.findViewById<TextView>(R.id.txtType)
        txtName.text = kostList[position].name
        txtRating.text = "Rating : " +kostList[position].rating
        txtPrice.text = "Rp. " + kostList[position].price
        txtType.text = kostList[position].type

        val id = kostList[position].id
        val kostId = id.toString()
        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetail)
        btnDetail.setOnClickListener {
            val action = KostListFragmentDirections.actionToDetail(kostId)
            Navigation.findNavController(it).navigate(action)
        }
        var imageView = holder.view.findViewById<ImageView>(R.id.imageView)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressbar)
        imageView.loadImage(kostList[position].photo, progressBar)
    }
    fun updateKostList(newKostList: ArrayList<Kost>) {
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }
}
