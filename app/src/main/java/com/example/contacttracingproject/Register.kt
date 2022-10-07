package com.example.contacttracingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.contacttracingproject.data.BaseResponse
import com.example.contacttracingproject.databinding.ActivityRegisterBinding
import com.example.contacttracingproject.viewModel.RegisterViewModel


class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.registerResult.observe(this){
            when (it){
                is BaseResponse.Error -> {
                    Toast.makeText(this, "User Exist", Toast.LENGTH_SHORT).show()
                }
                is BaseResponse.Success -> {
                    Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                        .putExtra("icNumber", binding.icNumber.text.toString())
                    startActivity(intent)
                }
                else -> {

                }
            }
        }
//
        binding.registerButton.setOnClickListener {
            if(binding.icNumber.text.toString().isEmpty() || binding.password.text.toString().isEmpty() || binding.password2.text.toString().isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            }else if (binding.password.text.toString() != binding.password2.text.toString()) {
                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.register(binding.icNumber.text.toString(), binding.password.text.toString())
            }
        }
//
        binding.signIn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }

}