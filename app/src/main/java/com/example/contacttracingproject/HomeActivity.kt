package com.example.contacttracingproject

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.print.PrintHelper.ORIENTATION_PORTRAIT
import com.example.contacttracingproject.data.BaseResponse
import com.example.contacttracingproject.repository.HistoryRepository
import com.example.contacttracingproject.roomDatabase.HistoryDatabase
import com.example.contacttracingproject.viewModel.HistoryViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class HomeActivity : AppCompatActivity() {
    private val historyViewModel by viewModels<HistoryViewModel>()

    val zxingQRCode = registerForActivityResult(ScanContract()){
            result ->
        val intent = intent
        if(result.contents != null){
            Toast.makeText(applicationContext, "Scanned", Toast.LENGTH_LONG).show()

            val icNumber = intent.getStringExtra("icNumber").toString()
            val location = result.contents.toString().split(",").toMutableList()[0]

            val datetime = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " ~ " + LocalTime.now().format(
                DateTimeFormatter.ofPattern("HH:mm"))

            historyViewModel.insertHistory(icNumber, location, datetime)

            historyViewModel.historyResult.observe(this){
                when(it){
                    is BaseResponse.Success -> {
                        Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_home, History.newInstance()).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_home, Profile.newInstance())
            .commit()

        val bottom_nav_view = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        bottom_nav_view.setOnItemSelectedListener {
                item ->
            when (item.itemId) {
                R.id.nav_faq -> {
                    //navController.navigate(R.id.homeFragment)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_home, FAQ.newInstance())
                        .commit()
                    true
                }
                R.id.nav_history -> {
                    //navController.navigate(R.id.historyFragment)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_home, History.newInstance())
                        .commit()
                    true
                }
                R.id.nav_scan -> {
                    val options = ScanOptions()
                    options.apply {
                        setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                        setPrompt("Scan Location QRCODE")
                        setCameraId(0)
                        setBeepEnabled(true)
                        setBarcodeImageEnabled(true)
                        setOrientationLocked(true)
                        setTorchEnabled(true)
                    }
                    zxingQRCode.launch(options)
                    true
                }
                R.id.nav_hotspot -> {
                    //navController.navigate(R.id.statisticsFragment)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_home, Hotspot.newInstance())
                        .commit()
                    true
                }
                R.id.nav_profile -> {
                    //navController.navigate(R.id.profileFragment)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_home, Profile.newInstance())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}