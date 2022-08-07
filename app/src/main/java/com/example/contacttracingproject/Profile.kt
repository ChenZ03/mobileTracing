package com.example.contacttracingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent as Intent

class Profile : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        val details : LinearLayout = itemView.findViewById(R.id.details)
        details.setOnClickListener{
            val intent = Intent(activity, VaccinationDetails::class.java)
            startActivity(intent)
        }

        val logout : Button = itemView.findViewById(R.id.logout)
        logout.setOnClickListener{
            val intent = Intent(activity, Register::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance() = Profile()
    }
}