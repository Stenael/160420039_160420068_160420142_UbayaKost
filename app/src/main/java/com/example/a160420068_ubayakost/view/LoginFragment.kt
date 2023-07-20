package com.example.a160420068_ubayakost.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420068_ubayakost.Global
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.viewModel.ProfileViewModel
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val txtUsername = view.findViewById<TextInputEditText>(R.id.txtUsername)
        val txtPass = view.findViewById<TextInputEditText>(R.id.txtAddress)


        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            var username = txtUsername?.text.toString()
            var pass = txtPass?.text.toString()
            profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
            profileViewModel.checkLogin(username,pass)

            observeViewModel()

            if(profileViewModel.profileLD.value?.username == username && profileViewModel.profileLD.value?.password == pass){
                Global.username = username
                Global.password = pass
                val action = LoginFragmentDirections.loginToList()
                Navigation.findNavController(it).navigate(action)
            }else{
                Toast.makeText(activity,"Username or Password wrong",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun observeViewModel(){
        profileViewModel.profileLD.observe(viewLifecycleOwner,Observer{
            profileViewModel.profileLD.value?.username.toString()
            profileViewModel.profileLD.value?.password.toString()
        })
    }
}