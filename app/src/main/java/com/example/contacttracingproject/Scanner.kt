package com.example.contacttracingproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.contacttracingproject.repository.HistoryRepository
import com.example.contacttracingproject.roomDatabase.HistoryDatabase
import com.example.contacttracingproject.viewModel.HistoryViewModel
import com.example.contacttracingproject.viewModel.HistoryViewModelFactory
import com.journeyapps.barcodescanner.ScanContract
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Scanner : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scanner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



    companion object {
        fun newInstance() = Scanner()
    }

}