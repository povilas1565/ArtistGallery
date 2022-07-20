package com.example.artgallery.domain

import com.example.artgallery.domain.model.User
import com.example.artgallery.utils.Request
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun auth(login: String, password: String): Flow<Request<User>>
}