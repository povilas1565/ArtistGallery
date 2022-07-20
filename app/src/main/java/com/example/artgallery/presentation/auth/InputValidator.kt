package com.example.artgallery.presentation.auth

import androidx.lifecycle.MutableLiveData

class InputValidator {
private val mutableLoginError = MutableLiveData<LoginError>()
private val mutablePasswordError = MutableLiveData<PasswordError>()

fun isFieldsValid(login: String, password: String): Boolean {
    val isLoginValid = when {
        login.isEmpty() -> {
            mutableLoginError.value = LoginError.EMPTY
            false
        }
        login.length != 10 -> {
            mutableLoginError.value = LoginError.VALID
            false
        }
        else -> {
            mutableLoginError.value = LoginError.NOT_VALID
            true
        }
    }

    val isPasswordValid = when {
        password.isEmpty() -> {
            mutablePasswordError.value = PasswordError.EMPTY
            false
        }
        password.length !in 6..255 -> {
            mutablePasswordError.value = PasswordError.NOT_VALID
            false
        }
        else -> {
            mutablePasswordError.value = PasswordError.VALID
            true
        }
    }
    return isLoginValid && isPasswordValid
}
}

enum class LoginError {
    EMPTY,
    NOT_VALID,
    VALID
}

enum class PasswordError {
    EMPTY,
    NOT_VALID,
    VALID
}



}