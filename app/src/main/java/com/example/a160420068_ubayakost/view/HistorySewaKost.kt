package com.example.a160420068_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420068_ubayakost.R
import com.example.a160420068_ubayakost.viewModel.ListViewModel


class HistorySewaKost : Fragment() {
    private lateinit var viewModel:ListViewModel
    private val historyAdapter = HistoryListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_sewa_kost, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.fetch()
        var recView = view.findViewById<RecyclerView>(R.id.recViewHistory)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = historyAdapter

        ObserverViewModel()
    }

    fun ObserverViewModel(){
        viewModel.historyLD.observe(viewLifecycleOwner, Observer {
            historyAdapter.updateHistoryList(it)
        })
    }
}