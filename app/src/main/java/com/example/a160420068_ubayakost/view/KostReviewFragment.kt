package com.example.a160420068_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.viewModel.DetailViewModel
import com.squareup.picasso.Picasso

class KostReviewFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_review, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val kostId = KostDetailFragmentArgs.fromBundle(requireArguments()).id
        detailViewModel.refresh(kostId)

        val txtNameDet = view.findViewById<TextView>(R.id.txtNameDet)
        val txtDetail = view.findViewById<TextView>(R.id.txtDetail)
        val txtRev1 = view.findViewById<TextView>(R.id.txtReview1)
        val txtRev2 = view.findViewById<TextView>(R.id.txtReview2)
        val imageView = view.findViewById<ImageView>(R.id.imageViewDet)
        detailViewModel.kostLD.observe(viewLifecycleOwner)
        { kostDetail ->
            txtNameDet?.setText(kostDetail.name.toString())
            txtDetail?.setText(kostDetail.desc.toString())
            txtRev1?.setText(kostDetail.rev1.toString())
            txtRev2?.setText(kostDetail.rev2.toString())

            var url = kostDetail.photo
            Picasso.get()
                .load(url)
                .resize(400, 400)
                .centerCrop()
                .error(R.drawable.baseline_error_24)
                .into(imageView)
        }
    }


}