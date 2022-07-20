package com.example.artgallery.di

import com.example.artgallery.data.AuthRepositoryImpl
import com.example.artgallery.data.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object AuthModule  {

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun providesAuthRepository(authService: AuthService) = AuthRepositoryImpl(authService)
}