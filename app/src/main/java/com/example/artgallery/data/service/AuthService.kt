package com.example.artgallery.data.service

import com.example.artgallery.data.model.AuthRequest
import com.example.artgallery.data.model.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    suspend fun auth(@Body authRequest: AuthRequest) : AuthResponse
}