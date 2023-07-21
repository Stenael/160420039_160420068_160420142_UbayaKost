package com.example.a160420068_ubayakost.view

import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420068_ubayakost.Global
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.databinding.FragmentKostDetailBinding
import com.example.a160420068_ubayakost.databinding.FragmentKostEditBinding
import com.example.a160420068_ubayakost.databinding.FragmentProfileBinding
import com.example.a160420068_ubayakost.model.Kost
import com.example.a160420068_ubayakost.viewModel.ProfileViewModel
import com.google.android.material.textfield.TextInputEditText

class ProfileFragment : Fragment(), ButtonEditProfileClickListener, ButtonChangePasswordListener{


    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentProfileBinding.inflate(inflater,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        profileViewModel.refresh(Global.username)
        profileViewModel.checkLogin(Global.username,Global.password)
        dataBinding.editProfile = this
        dataBinding.changePass = this

        observeViewModel()

    }
    fun observeViewModel(){
        profileViewModel.profileLD.observe(viewLifecycleOwner, Observer {
            dataBinding.profile = it
        })
    }
    override fun onButtonEditProfileClick(v: View, profile: com.example.a160420068_ubayakost.model.Profile){
        profileViewModel.updateProfile(profile.address,profile.number,profile.id)
        Toast.makeText(view?.context,"Profile Updated", Toast.LENGTH_SHORT).show()
    }

    override fun onButtonChangePasswordClick(v: View) {
        val action = ProfileFragmentDirections.actionToChangePass(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

}