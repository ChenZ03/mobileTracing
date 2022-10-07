package com.example.contacttracingproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.contacttracingproject.data.BaseResponse
import com.example.contacttracingproject.databinding.ActivityEditprofileBinding
//import com.example.contacttracingproject.databinding.ActivityEditprofileBinding
import com.example.contacttracingproject.viewModel.EditProfileViewModel


class EditProfile : AppCompatActivity() {

    private lateinit var binding: ActivityEditprofileBinding
    private val viewModel: EditProfileViewModel by viewModels()
    private var icNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val intent = getIntent()
        icNumber = intent.getStringExtra("icNumber").toString()

        viewModel.editResult.observe(this) {
            when (it) {
                is BaseResponse.Success -> {
                    Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("icNumber", icNumber)
                    startActivity(intent)
                }
                is BaseResponse.Error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.saveButton.setOnClickListener {
            if(binding.newName.text.toString().isEmpty() || binding.newPhone.text.toString().isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.editProfile(icNumber, binding.newName.text.toString(), binding.newPhone.text.toString())
            }
        }

        binding.cancelEdit.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("icNumber", icNumber)
            startActivity(intent)
        }
    }
//


}
