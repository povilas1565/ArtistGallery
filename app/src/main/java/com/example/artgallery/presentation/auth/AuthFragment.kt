package com.example.artgallery.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.artgallery.R
import com.example.artgallery.databinding.FragmentAuthBinding
import com.example.artgallery.presentation.auth.LoginError.*
import com.example.artgallery.utils.LoadState
import com.google.android.material.snackbar.Snackbar
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.MaskedTextChangedListener.Companion.installOn
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 */
@Suppress("RedundantNullableReturnType")
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
        observePasswordError()
        observeLoadState()

        binding.authBtn.setOnClickListener {
            viewModel.auth()
        }

        binding.passwordEdt.doOnTextChanged { password, _, _, _ ->
            viewModel.setPassword(password.toString())
        }
    }


    private fun initLoginMask() {
        installOn(
            binding.loginEdt,
            PHONE_MASK,
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String
                ) {
                    viewModel.setLogin(extractedValue)
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

    private fun observePasswordError() {
        viewModel.mutableLoginError.observe(viewLifecycleOwner) { passwordError ->
            when (passwordError) {
                passwordError.EMPTY -> {
                    binding.passwordTil.error = getString(R.string.password_empty_error)
                }
                passwordError.NOT_VALID -> {
                    binding.passwordTil.error = getString(R.string.password_not_valid_error)
                }
                passwordError.VALID -> {
                    binding.passwordTil.error = null
                }
                else -> {
                    // do nothing
                }
            }
        }
    }

    private fun observeLoadState() {
        viewModel.loadState.observe(viewLifecycleOwner) { loadState ->
            when (loadState) {
                LoadState.LOADING -> {
                    binding.authBtn.isLoading = true
                    binding.blockContentContainer.isVisible = true
                }
                LoadState.ERROR -> {
                    binding.authBtn.isLoading = false
                    binding.blockContentContainer.isVisible = false
                    Snackbar.make(
                        binding.root,
                        "Логин или пароль введен неправильно",
                        Snackbar.LENGTH_LONG
                    ).setAnchorView(binding.authBtn).show()
                }
                LoadState.SUCCESS -> {
                    binding.authBtn.isLoading = false
                    binding.blockContentContainer.isVisible = false
                    findNavController().navigate(R.id.action_authFragment_to_mainFragment)
                }
                else -> {
                    // Do nothing

                }
            }
        }
    }

    companion object {
        const val PHONE_MASK = "+7 ([000]) [000] [00] [00]"
    }
}






