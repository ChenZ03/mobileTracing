package com.example.contacttracingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.contacttracingproject.`interface`.UserRetrofit
import com.example.contacttracingproject.`object`.baseApi
import com.example.contacttracingproject.databinding.ActivityLoginBinding
import com.example.contacttracingproject.repository.UserRepository
import com.example.contacttracingproject.roomDatabase.userDatabase
import com.example.contacttracingproject.viewModel.LoginViewModel
import com.example.contacttracingproject.viewModel.LoginViewModelFactory

class Login : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userRetrofit = baseApi.getInstance().create(UserRetrofit::class.java)
        val repository = UserRepository(userRetrofit)
        val factory = LoginViewModelFactory(repository)

        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this

        loginViewModel.errorToast.asLiveData().observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            loginViewModel._finish.value = false
        }

        loginViewModel.finish.observe(this, Observer { isFinished ->
            if (isFinished == true) {
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                    .putExtra("icNumber", loginViewModel.icNumber.value.toString())
                startActivity(intent)
            }
        })

        binding.loginButton.setOnClickListener {
            if(binding.icNumber.text.toString().isEmpty() || binding.password.text.toString().isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            }else{
                loginViewModel.login()
            }
        }

        binding.signUp.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }



}