package com.example.dailyquotes.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dailyquotes.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private val activityContext by lazy { requireActivity() }
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        setupThemeChangeListeners()

        binding.linkedIn.setOnClickListener {
            openLinkedInProfile("https://www.linkedin.com/in/malaika-akhtar-khan-903314251")
        }
        binding.linkedIn.setOnClickListener {
            openLinkedInProfile("https://www.linkedin.com/in/malaika-akhtar-kha")
        }


        binding.gitHub.setOnClickListener {
            openLinkedInProfile("https://github.com/MalaikaAkhtar")
        }

        return (binding.root)

    }

    private fun openLinkedInProfile(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun setupThemeChangeListeners() {

        binding.pinkIV.setOnClickListener {
            saveTheme("pink_theme")
        }
        binding.blueIV.setOnClickListener {
            saveTheme("blue_theme")
        }

        binding.greenIV.setOnClickListener {
            saveTheme("green_theme")
        }

        binding.blackIV.setOnClickListener {
            saveTheme("blackleaves_theme")
        }

        binding.yellowIV.setOnClickListener {
            saveTheme("yellow_theme")
        }

        binding.purpleIV.setOnClickListener {
            saveTheme("purple_theme")
        }

    }

    private fun saveTheme(theme: String) {
        val sharedPreferences = activityContext.getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("selected_theme", theme)
        editor.apply()
        Toast.makeText(requireContext(), "Theme saved: $theme", Toast.LENGTH_SHORT).show()
    }

}