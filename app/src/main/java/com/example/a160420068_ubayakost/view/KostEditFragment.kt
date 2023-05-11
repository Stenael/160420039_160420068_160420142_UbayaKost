package com.example.a160420068_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.viewModel.DetailViewModel
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class KostEditFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_edit, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val kostId = KostDetailFragmentArgs.fromBundle(requireArguments()).id
        detailViewModel.refresh(kostId)

        val txtNameDet = view.findViewById<TextView>(R.id.txtNameDet)
        val txtDetail = view.findViewById<TextView>(R.id.txtDetail)
        val imageView = view.findViewById<ImageView>(R.id.imageViewDet)
        val txtAddress = view.findViewById<TextInputEditText>(R.id.txtAddress)
        val txtPrice = view.findViewById<TextInputEditText>(R.id.txtPrice)
        val txtType = view.findViewById<TextInputEditText>(R.id.txtType)
        detailViewModel.kostLD.observe(viewLifecycleOwner)
        { kostDetail ->
            txtNameDet?.setText(kostDetail.name.toString())
            txtDetail?.setText(kostDetail.desc.toString())
            txtAddress.setText(kostDetail.addresss.toString())
            txtPrice.setText(kostDetail.price.toString())
            txtType.setText(kostDetail.type.toString())

            var url = kostDetail.photo
            Picasso.get()
                .load(url)
                .resize(400, 400)
                .centerCrop()
                .error(R.drawable.baseline_error_24)
                .into(imageView)
        }
        val btnDone = view.findViewById<Button>(R.id.btnBook)
        btnDone.setOnClickListener {
            val action = KostEditFragmentDirections.editToList()
            Navigation.findNavController(it).navigate(action)
        }
    }
}