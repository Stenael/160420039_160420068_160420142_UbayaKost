package com.example.a160420068_ubayakost.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.databinding.FragmentKostBookBinding
import com.example.a160420068_ubayakost.databinding.FragmentKostDetailBinding
import com.example.a160420068_ubayakost.model.HistorySewa
import com.example.a160420068_ubayakost.viewModel.DetailViewModel
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class KostBookFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var dataBinding: FragmentKostBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentKostBookBinding.inflate(inflater,container,false)
        return dataBinding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val kostId = KostDetailFragmentArgs.fromBundle(requireArguments()).id
        var txtViewDays = view.findViewById<TextView>(R.id.txtViewDays)
        var txtNameKost = view.findViewById<TextView>(R.id.txtNameDet)
        val txtAddDays = view.findViewById<TextInputEditText>(R.id.txtDays)
        detailViewModel.refresh(kostId)
        var btnBook = view.findViewById<Button>(R.id.btnBook)
        btnBook.setOnClickListener{
            val current = LocalDate.now().plusDays(txtAddDays.text.toString().toLong())
            txtViewDays?.text = current.toString()
            var history = HistorySewa(txtNameKost.text.toString(), txtViewDays.text.toString())
            val list = listOf(history)
            detailViewModel.InsertHistory(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
        }

        observeViewModel()
    }

    fun observeViewModel(){
        detailViewModel.kostLD.observe(viewLifecycleOwner, Observer {
            dataBinding.kost = it
        })
    }

    fun ImageView.loadImage(url:String?){
        Picasso.get()
            .load(url)
            .resize(400, 400)
            .centerCrop()
            .error(R.drawable.baseline_error_24)
            .into(this)
    }

}