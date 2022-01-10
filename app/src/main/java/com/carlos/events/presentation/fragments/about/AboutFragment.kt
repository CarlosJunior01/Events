package com.carlos.events.presentation.fragments.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.carlos.events.R
import com.carlos.events.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAboutBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAboutBackButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_aboutFragment_to_eventsFragment)
        }

        binding.txtAboutDev.setOnClickListener {
            openLinkedinDev()
        }
    }

    private fun openLinkedinDev() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(HTTP_LINKEDIN))
        startActivity(browserIntent)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_aboutFragment_to_eventsFragment)}
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)}

    companion object {
        private const val HTTP_LINKEDIN = "https://www.linkedin.com/in/carlosjrdeveloper"
    }
}