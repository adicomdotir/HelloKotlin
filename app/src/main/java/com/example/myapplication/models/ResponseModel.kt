package com.example.myapplication.models

data class ResponseModel(
    val token_type: String,
    val expires_in: Int,
    val access_token: String,
    val refresh_token: String
)