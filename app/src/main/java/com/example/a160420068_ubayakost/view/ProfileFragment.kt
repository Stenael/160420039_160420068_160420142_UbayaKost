package com.example.a160420068_ubayakost.view

import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160420068_ubayakost.Global
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.databinding.FragmentKostDetailBinding
import com.example.a160420068_ubayakost.databinding.FragmentProfileBinding
import com.example.a160420068_ubayakost.viewModel.ProfileViewModel
import com.google.android.material.textfield.TextInputEditText

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater,R.layout.fragment_profile,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        profileViewModel.fetch(Global.username, Global.password)

        observeViewModel()

    }
    fun observeViewModel(){
        profileViewModel.profileLD.observe(viewLifecycleOwner, Observer {
            dataBinding.profile = it
        })
//        val txtUsername = view?.findViewById<TextInputEditText>(R.id.txtUsername)
//        val txtAddress = view?.findViewById<TextInputEditText>(R.id.txtAddress)
//        val txtNumber = view?.findViewById<TextInputEditText>(R.id.txtNumber)
//
//        profileViewModel.profileLD.observe(viewLifecycleOwner, Observer{
//            txtUsername?.setText(profileViewModel.profileLD.value?.username)
//            txtAddress?.setText(profileViewModel.profileLD.value?.address)
//            txtNumber?.setText(profileViewModel.profileLD.value?.number)
//        })
    }
}