package com.example.contacttracingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.contacttracingproject.`interface`.UserRetrofit
import com.example.contacttracingproject.`object`.baseApi
import com.example.contacttracingproject.databinding.ActivityRegisterBinding
import com.example.contacttracingproject.repository.UserRepository
import com.example.contacttracingproject.roomDatabase.userDatabase
import com.example.contacttracingproject.viewModel.RegisterViewModel
import com.example.contacttracingproject.viewModel.RegisterViewModelFactory
import org.w3c.dom.Text

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel : RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userRetrofit = baseApi.getInstance().create(UserRetrofit::class.java)
        val repository = UserRepository(userRetrofit)
        val factory = RegisterViewModelFactory(repository)

        registerViewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)
        binding.viewModel = registerViewModel
        binding.lifecycleOwner = this

        registerViewModel.errorToast.asLiveData().observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            registerViewModel._finish.value = false
        }

        registerViewModel.finish.observe(this, Observer { isFinished ->
            if (isFinished == true) {
                Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                    .putExtra("icNumber", registerViewModel.icNumber.value.toString())
                startActivity(intent)
            }
        })

        binding.registerButton.setOnClickListener {
            if(binding.icNumber.text.toString().isEmpty() || binding.password.text.toString().isEmpty() || binding.password2.text.toString().isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            }else{
                registerViewModel.register()
            }
        }

        binding.signIn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }

}