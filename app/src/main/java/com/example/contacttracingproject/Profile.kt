package com.example.contacttracingproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacttracingproject.application.BaseApplication
import com.example.contacttracingproject.databinding.ActivityEditprofileBinding
import com.example.contacttracingproject.databinding.FragmentProfileBinding
import com.example.contacttracingproject.viewModel.EditProfileViewModel
import com.example.contacttracingproject.viewModel.ProfileViewModel
import com.example.contacttracingproject.viewModel.ProfileViewModelFactory
import android.content.Intent as Intent

class Profile : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel : ProfileViewModel
    private var icNumber: String = ""

    private var layoutManager: RecyclerView.LayoutManager? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        val intent = requireActivity().intent
        icNumber = intent.getStringExtra("icNumber").toString()
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

//        val profileViewModel: ProfileViewModel by viewModels {
//            ProfileViewModelFactory
//        }

        binding.viewModel = profileViewModel

        binding.lifecycleOwner = this

        profileViewModel.getUser(icNumber)

        val details : LinearLayout = itemView.findViewById(R.id.details)
        details.setOnClickListener{
            val intent = Intent(activity, VaccinationDetails::class.java)
            startActivity(intent)
        }

        val editProfile : ImageView = itemView.findViewById(R.id.editProfile)
        editProfile.setOnClickListener{
            val intent = Intent(activity, EditProfile::class.java)
                .putExtra("icNumber", profileViewModel.icNumber.value.toString())
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