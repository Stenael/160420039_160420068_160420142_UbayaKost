package com.example.a160420068_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.databinding.FragmentKostBookBinding
import com.example.a160420068_ubayakost.databinding.FragmentKostDetailBinding
import com.example.a160420068_ubayakost.viewModel.DetailViewModel
import com.squareup.picasso.Picasso

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

    fun ImageView.loadImage(url:String?){
        Picasso.get()
            .load(url)
            .resize(400, 400)
            .centerCrop()
            .error(R.drawable.baseline_error_24)
            .into(this)
    }

//        val txtNameDet = view.findViewById<TextView>(R.id.txtNameDet)
//        val txtDetail = view.findViewById<TextView>(R.id.txtDetail)
//        val txtFal1 = view.findViewById<TextView>(R.id.txtFal1)
//        val txtFal2 = view.findViewById<TextView>(R.id.txtFal2)
//        val txtFal3 = view.findViewById<TextView>(R.id.txtFal3)
//        val imageView = view.findViewById<ImageView>(R.id.imageViewDet)
//        detailViewModel.kostLD.observe(viewLifecycleOwner)
//        { kostDetail ->
//            txtNameDet?.setText(kostDetail.name.toString())
//            txtDetail?.setText(kostDetail.desc.toString())
//            txtFal1?.setText(kostDetail.fal1.toString())
////            txtFal2?.setText(kostDetail.fal2.toString())
////            txtFal3?.setText(kostDetail.fal3.toString())
//
//            var url = kostDetail.photo
//            Picasso.get()
//                .load(url)
//                .resize(400, 400)
//                .centerCrop()
//                .error(R.drawable.baseline_error_24)
//                .into(imageView)
//        }
}