package com.example.a160420068_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.viewModel.ProfileViewModel

class ChangePasswordFragment : Fragment() {
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        val id = ChangePasswordFragmentArgs.fromBundle(requireArguments()).id
        val txtNewPass = view.findViewById<EditText>(R.id.txtNewPassword)
        val txtConfirmPass = view.findViewById<EditText>(R.id.txtConfirmPassword)
        val btnConfirm = view.findViewById<Button>(R.id.btnConfirm)

        btnConfirm.setOnClickListener {
            if(txtNewPass.text.toString() == txtConfirmPass.text.toString()){
                profileViewModel.changePassword(txtNewPass.text.toString(),id.toInt())
                Toast.makeText(view.context,"Password Changed", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(view.context,"Password Change Failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}