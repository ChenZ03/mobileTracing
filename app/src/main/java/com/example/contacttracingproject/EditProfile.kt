package com.example.contacttracingproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.contacttracingproject.databinding.ActivityEditprofileBinding
import com.example.contacttracingproject.repository.UserRepository
import com.example.contacttracingproject.roomDatabase.userDatabase
import com.example.contacttracingproject.viewModel.EditProfileViewModel
import com.example.contacttracingproject.viewModel.EditProfileViewModelFactory
import com.google.android.material.button.MaterialButton


class EditProfile : AppCompatActivity(){

    private lateinit var binding: ActivityEditprofileBinding
    private lateinit var editProfileViewModel : EditProfileViewModel
    private var icNumber : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = getIntent()
        icNumber = intent.getStringExtra("icNumber").toString()

        val dao = userDatabase.getInstance(application).userDao()
        val repository = UserRepository(dao)
        val factory = EditProfileViewModelFactory(repository)

        editProfileViewModel = ViewModelProvider(this, factory).get(EditProfileViewModel::class.java)
        binding.viewModel = editProfileViewModel
        binding.lifecycleOwner = this

        editProfileViewModel.errorToast.asLiveData().observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            editProfileViewModel._finish.value = false
        }

        editProfileViewModel.finish.observe(this, Observer { isFinished ->
            if (isFinished == true) {
                Toast.makeText(this, "Edited Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                    .putExtra("icNumber", editProfileViewModel.icNumber.value.toString())
                startActivity(intent)
            }
        })


        binding.saveButton.setOnClickListener {
            if(binding.newName.text.toString().isEmpty() || binding.newPhone.text.toString().isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            }else{
                editProfileViewModel.editProfile(icNumber, binding.newName.text.toString(), binding.newPhone.text.toString())
            }
        }

        binding.cancelEdit.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

}