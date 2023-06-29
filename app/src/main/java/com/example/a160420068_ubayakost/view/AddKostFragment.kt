package com.example.a160420068_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.model.Kost
import com.example.a160420068_ubayakost.viewModel.DetailViewModel
import com.google.android.material.textfield.TextInputEditText


class AddKostFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_kost, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(DetailViewModel::class.java)

        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val txtNamaKost = view.findViewById<TextInputEditText>(R.id.txtNamaKost)
            val txtDescKost = view.findViewById<TextInputEditText>(R.id.txtDesc)
            val txtTypeKost = view.findViewById<TextInputEditText>(R.id.txtKostType)
            val txtAddressKost = view.findViewById<TextInputEditText>(R.id.txtKostAddress)
            val txtPriceKost = view.findViewById<TextInputEditText>(R.id.txtKostPrice)
            val txtPhotoUrl = view.findViewById<TextInputEditText>(R.id.txtKostPhoto)
            val txtFacilityKost = view.findViewById<TextInputEditText>(R.id.txtKostFacility)

            var kost = Kost(txtNamaKost.text.toString(), txtDescKost.text.toString(), txtTypeKost.text.toString(), txtAddressKost.text.toString(),
                            txtPriceKost.text.toString(), txtPhotoUrl.text.toString(), txtFacilityKost.text.toString())
            val list = listOf(kost)
            viewModel.addTodo(list)

            Toast.makeText(view.context, "Data Added", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}