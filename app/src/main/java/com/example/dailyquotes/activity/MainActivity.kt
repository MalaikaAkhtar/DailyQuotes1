package com.example.dailyquotes.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.dailyquotes.R
import com.example.dailyquotes.databinding.ActivityMainBinding
import com.example.dailyquotes.fragment.FavoriteFragment
import com.example.dailyquotes.fragment.HomeFragment
import com.example.dailyquotes.fragment.QuotesFragment
import com.example.dailyquotes.fragment.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = binding.bottomNavigation
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.bottom_quotes -> {
                    replaceFragment(QuotesFragment())
                    true
                }

                R.id.bottom_favorites -> {
                    replaceFragment(FavoriteFragment())
                    true
                }

                R.id.bottom_settings -> {
                    replaceFragment(SettingsFragment())
                    true
                }

                else -> false
            }
        }
        replaceFragment(HomeFragment())


    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameContainer, fragment)
            .commit()
    }

}
