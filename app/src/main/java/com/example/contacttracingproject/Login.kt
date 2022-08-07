package com.example.contacttracingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.contacttracingproject.application.UserApplication
import com.example.contacttracingproject.databinding.ActivityLoginBinding
import com.example.contacttracingproject.viewModel.UserViewModel
import com.example.contacttracingproject.viewModel.UserViewModelFactory

class Login : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(
            (application as UserApplication).repository
        )
    }

    companion object{
        val userID = "com.example.contacttracingproject.USER_ID"
        val username = "com.example.contacttracingproject.USER_NAME"
        val password = "com.example.contacttracingproject.PASSWORD"
        val icNumber = "com.example.contacttracingproject.IC_NUMBER"
        val phone = "com.example.contacttracingproject.PHONE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val ic = binding.icNumber.text.toString()
            val pw = binding.password.text.toString()


            if(ic.isEmpty() || pw.isEmpty()){
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            }else{
                //show data of login
                userViewModel.loginUser(ic,pw).observe(this) {
                    if (it != null) {
//                        val intent = Intent(this, HomeActivity::class.java)
//                        intent.putExtra(userID, it.id)
//                        intent.putExtra(username, it.username)
//                        intent.putExtra(password, it.password)
//                        intent.putExtra(icNumber, it.icNumber)
//                        intent.putExtra(phone, it.phone)
//                        startActivity(intent)
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    }  else {
                        Toast.makeText(this, "Invalid IC Number or Password", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        binding.signUp.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }



}