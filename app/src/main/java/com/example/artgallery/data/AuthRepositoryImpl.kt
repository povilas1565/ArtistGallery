package com.example.artgallery.data

import com.example.artgallery.data.mapper.mapToDomain
import com.example.artgallery.data.model.AuthRequest
import com.example.artgallery.data.service.AuthService
import com.example.artgallery.domain.AuthRepository
import com.example.artgallery.domain.model.User
import com.example.artgallery.utils.Request
import com.example.artgallery.utils.RequestUtils.requestFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun auth(login: String, password: String): Flow<Request<User>> {
        return requestFlow {
            val authResponse = authService.auth(AuthRequest(login, password))
            val user = authResponse.mapToDomain()
            user
        }
    }
}
