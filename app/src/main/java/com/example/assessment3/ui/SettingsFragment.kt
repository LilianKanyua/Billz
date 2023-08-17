package com.example.assessment3.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.assessment3.R
import com.example.assessment3.utils.Constants


class SettingsFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val logoutButton = view.findViewById<TextView>(R.id.btnLogout)

        logoutButton.setOnClickListener {

            performLogout()
        }

        return view
    }

    private fun performLogout() {
        val sharedPrefs = requireActivity().getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)

        val editor = sharedPrefs.edit()
        editor.clear().apply()

        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        requireActivity().finish()
        Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()

    }
    }


