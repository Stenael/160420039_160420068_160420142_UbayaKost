package com.example.a160420068_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.databinding.FragmentKostBookBinding
import com.example.a160420068_ubayakost.databinding.FragmentKostDetailBinding
import com.example.a160420068_ubayakost.databinding.FragmentKostEditBinding
import com.example.a160420068_ubayakost.databinding.FragmentKostEditBindingImpl
import com.example.a160420068_ubayakost.viewModel.DetailViewModel
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class KostEditFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var dataBinding: FragmentKostEditBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentKostEditBinding.inflate(inflater,container,false)
        return dataBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val kostId = KostDetailFragmentArgs.fromBundle(requireArguments()).id
        detailViewModel.refresh(kostId)

        observeViewModel()
    }
    fun observeViewModel(){
        detailViewModel.kostLD.observe(viewLifecycleOwner, Observer {
            dataBinding.kost = it
        })
    }

}