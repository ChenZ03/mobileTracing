package com.example.contacttracingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacttracingproject.adapter.HistoryAdapter
import com.example.contacttracingproject.application.BaseApplication
import com.example.contacttracingproject.databinding.FragmentHistoryBinding
import com.example.contacttracingproject.viewModel.HistoryViewModel
import com.example.contacttracingproject.viewModel.HistoryViewModelFactory

class History : Fragment() {

    private var adapter = HistoryAdapter()
    lateinit var binding: FragmentHistoryBinding

    private val historyViewModel : HistoryViewModel by viewModels{
        HistoryViewModelFactory((activity?.application as BaseApplication).historyRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
//        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.locationView.adapter = adapter
        binding.locationView.layoutManager = LinearLayoutManager(activity)
        historyViewModel.histories.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        fun newInstance() = History()
    }
}