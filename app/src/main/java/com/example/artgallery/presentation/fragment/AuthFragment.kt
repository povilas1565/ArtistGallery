package com.example.artgallery.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.artgallery.R
import com.example.artgallery.databinding.FragmentAuthBinding
import com.google.android.material.snackbar.Snackbar

/**
 *
 */
class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.authBtn.setOnClickListener {
           // findNavController().navigate(R.id.action_authFragment_to_mainFragment)
           //binding.authBtn.isLoading = true

            Snackbar.make(
                binding.root,
                resources.getString(R.string.auth_error_text),
                Snackbar.LENGTH_LONG
            ).setAnchorView(binding.authBtn).show()
        }
    }
}

