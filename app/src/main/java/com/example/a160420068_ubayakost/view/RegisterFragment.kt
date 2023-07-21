package com.example.a160420068_ubayakost.view

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.model.Profile
import com.example.a160420068_ubayakost.viewModel.ProfileViewModel

class RegisterFragment : Fragment() {

    private lateinit var  profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        var txtUsername = view.findViewById<EditText>(R.id.txtUsername)
        var txtPassword = view.findViewById<EditText>(R.id.txtPassword)
        var txtAddress = view.findViewById<EditText>(R.id.txtAddress)
        var txtNumber = view.findViewById<EditText>(R.id.txtNumber)
        val btnSignUp = view.findViewById<Button>(R.id.btnSignUp)
        btnSignUp.setOnClickListener {
            var profile = Profile(txtUsername.text.toString(),txtPassword.text.toString(),txtAddress.text.toString(),txtNumber.text.toString())
            val list = listOf(profile)
            profileViewModel.register(list)
            Toast.makeText(view.context, "Account Created",Toast.LENGTH_LONG).show()
            val action = RegisterFragmentDirections.actionRegisterToList()
            Navigation.findNavController(it).navigate(action)
        }
    }

}