package com.example.artgallery.data.model

import com.google.gson.annotations.SerializedName

data class UserInfoObj(
    @SerializedName("id")
    val id: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("about")
    val about: String
)
