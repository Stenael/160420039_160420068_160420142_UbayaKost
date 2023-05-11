package com.example.a160420068_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.viewModel.ListViewModel
import com.example.a160420068_ubayakost.viewModel.RatingViewModel

class KostRatingFragment : Fragment() {
    private lateinit var ratingViewModel: RatingViewModel
    private val kostRatingAdapter = KostRatingAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_rating, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ratingViewModel = ViewModelProvider(this).get(RatingViewModel::class.java)
        ratingViewModel.refresh()
        val recView = view?.findViewById<RecyclerView>(R.id.recView)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = kostRatingAdapter

        observeViewModel()

        val refreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        val txtError = view?.findViewById<TextView>(R.id.txtError)
        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)

        refreshLayout?.setOnRefreshListener {
            recView?.visibility = View.GONE
            txtError?.visibility = View.GONE
            progressBar?.visibility = View.VISIBLE
            ratingViewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        ratingViewModel.kostsLD.observe(viewLifecycleOwner, Observer {
            kostRatingAdapter.updateKostList(it)
        })
        ratingViewModel.kostLoadingLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtError)
            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })
        ratingViewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recView = view?.findViewById<RecyclerView>(R.id.recView)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressBar)
            if(it == true) {
                recView?.visibility = View.GONE
                progressLoad?.visibility = View.VISIBLE
            } else {
                recView?.visibility = View.VISIBLE
                progressLoad?.visibility = View.GONE
            }
        })
    }
}