package com.example.contacttracingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
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
                    //navController.navigate(R.id.scanFragment)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_home, Scanner.newInstance())
                        .commit()
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