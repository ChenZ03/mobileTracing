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
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacttracingproject.application.BaseApplication
import com.example.contacttracingproject.data.BaseResponse
import com.example.contacttracingproject.databinding.ActivityEditprofileBinding
import com.example.contacttracingproject.databinding.FragmentProfileBinding
import com.example.contacttracingproject.viewModel.EditProfileViewModel
import com.example.contacttracingproject.viewModel.LoginViewModel
import com.example.contacttracingproject.viewModel.ProfileViewModel
import android.content.Intent as Intent

class Profile : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModels<ProfileViewModel>()
    private var icNumber: String = ""



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


        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.getUser(icNumber)

        viewModel.result.observe(viewLifecycleOwner){
            when (it){
                is BaseResponse.Success -> {
                    binding.ic.text = ("NRIC :" + it.data?.icNumber) ?: "NRIC :"
                    binding.name.text = ("Name :" + it.data?.username) ?: "Name :"
                    binding.phone.text = ("Phone :" + it.data?.phone) ?: "Phone :"
                }
                else -> {

                }
            }
        }

        val details : LinearLayout = itemView.findViewById(R.id.details)
        details.setOnClickListener{
            val intent = Intent(activity, VaccinationDetails::class.java)
            startActivity(intent)
        }

        val editProfile : ImageView = itemView.findViewById(R.id.editProfile)
        editProfile.setOnClickListener{
            val intent = Intent(activity, EditProfile::class.java)
                .putExtra("icNumber", icNumber)
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