package com.example.a160420068_ubayakost.view

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.databinding.FragmentKostDetailBinding
import com.example.a160420068_ubayakost.util.loadImage
import com.example.a160420068_ubayakost.util.loadPhotoURL
import com.example.a160420068_ubayakost.viewModel.DetailViewModel
import com.squareup.picasso.Picasso


class KostDetailFragment : Fragment(),ButtonEditClickListener,ButtonBookingClickListener,ButtonReviewClickListener {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var dataBinding:FragmentKostDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentKostDetailBinding.inflate(inflater,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val id = KostDetailFragmentArgs.fromBundle(requireArguments()).id
        detailViewModel.refresh(id)
        dataBinding.review = this
        dataBinding.edit = this
        dataBinding.booking = this

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

    override fun onButtonEditClick(v: View) {
        val action = KostDetailFragmentDirections.actionToEdit(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonReviewClick(v: View) {
        val action = KostDetailFragmentDirections.actionToReview(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonBookingClick(v: View) {
        val action = KostDetailFragmentDirections.actionToBook(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}