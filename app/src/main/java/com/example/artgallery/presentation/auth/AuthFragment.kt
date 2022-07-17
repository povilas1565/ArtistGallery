package com.example.artgallery.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.artgallery.R
import com.example.artgallery.databinding.FragmentAuthBinding
import com.example.artgallery.presentation.auth.LoginError.*
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.MaskedTextChangedListener.Companion.installOn
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 */
@AndroidEntryPoint
class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLoginMask()
        observeLoginError()

        binding.authBtn.setOnClickListener {
            // findNavController().navigate(R.id.action_authFragment_to_mainFragment)
            //binding.authBtn.isLoading = true

            // Snackbar.make(
            //    binding.root,
            //     resources.getString(R.string.auth_error_text),
            //    Snackbar.LENGTH_LONG
            // ).setAnchorView(binding.authBtn).show()
        }
    }

    private fun initLoginMask() {
        installOn(
            binding.loginEdt,
            PHONE_MASK,
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(maskFilled: Boolean, extractedValue: String, formattedValue: String) {
                 {
                    //viewModel.setLogin(extractedValue)
                }
                }
            }
        )
    }

    private fun observeLoginError() {
        viewModel.mutableLoginError.observe(viewLifecycleOwner) { loginError ->
            when (loginError) {
                loginError.EMPTY -> {
                    binding.loginTil.error = getString(R.string.login_empty_error)
                }
                loginError.NOT_VALID -> {
                    binding.loginTil.error = getString(R.string.login_not_valid_error)
                }
                loginError.VALID -> {
                    binding.loginTil.error = null
                }
                else -> {
                    // do nothing
                }
            }
        }
    }
    companion object {
        const val PHONE_MASK = "+7 ([000]) [000] [00] [00]"
    }
}









