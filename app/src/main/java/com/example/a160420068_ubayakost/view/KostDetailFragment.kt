package com.example.a160420068_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420068_ubayakost.Global
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.util.loadImage
import com.example.a160420068_ubayakost.viewModel.DetailViewModel
import com.squareup.picasso.Picasso


class KostDetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val kostId = KostDetailFragmentArgs.fromBundle(requireArguments()).id
        detailViewModel.refresh(kostId)

        val txtNameDet = view.findViewById<TextView>(R.id.txtNameDet)
        val txtDetail = view.findViewById<TextView>(R.id.txtDetail)
        val txtFal1= view.findViewById<TextView>(R.id.txtFal1)
        val txtFal2 = view.findViewById<TextView>(R.id.txtFal2)
        val txtFal3 = view.findViewById<TextView>(R.id.txtFal3)
        val imageView = view.findViewById<ImageView>(R.id.imageViewDet)
        detailViewModel.kostLD.observe(viewLifecycleOwner)
        { kostDetail ->
            txtNameDet?.setText(kostDetail.name.toString())
            txtDetail?.setText(kostDetail.desc.toString())
            txtFal1?.setText(kostDetail.fal1.toString())
            txtFal2?.setText(kostDetail.fal2.toString())
            txtFal3?.setText(kostDetail.fal3.toString())

            var url = kostDetail.photo
            Picasso.get()
                .load(url)
                .resize(400, 400)
                .centerCrop()
                .error(R.drawable.baseline_error_24)
                .into(imageView)
        }
        val btnEdit = view.findViewById<Button>(R.id.btnEdit)
        btnEdit.setOnClickListener {
            val action = KostDetailFragmentDirections.actionToEdit(kostId)
            Navigation.findNavController(it).navigate(action)
        }
        val btnReview = view.findViewById<Button>(R.id.btnReview)
        btnReview.setOnClickListener {
            val action = KostDetailFragmentDirections.actionToReview(kostId)
            Navigation.findNavController(it).navigate(action)
        }

        val btnBook = view.findViewById<Button>(R.id.btnBook)
        btnBook.setOnClickListener {
            val action = KostDetailFragmentDirections.actionToBook(kostId)
            Navigation.findNavController(it).navigate(action)
        }
    }
}