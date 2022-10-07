package com.example.contacttracingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.contacttracingproject.data.BaseResponse
import com.example.contacttracingproject.databinding.ActivityLoginBinding
import com.example.contacttracingproject.viewModel.LoginViewModel

class Login : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        viewModel.loginResult.observe(this){
            when (it){
                is BaseResponse.Error -> {
                    Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                }
                is BaseResponse.Success -> {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    .putExtra("icNumber", binding.icNumber.text.toString())
                    startActivity(intent)
                }
                else -> {

                }
            }
        }

        binding.loginButton.setOnClickListener {
            if(binding.icNumber.text.toString().isEmpty() || binding.password.text.toString().isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.loginUser(ic = binding.icNumber.text.toString(), pwd = binding.password.text.toString())
            }
        }

        binding.signUp.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

}