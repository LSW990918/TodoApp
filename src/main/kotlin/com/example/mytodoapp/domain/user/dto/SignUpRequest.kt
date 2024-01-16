package com.example.mytodoapp.domain.user.dto

data class SignUpRequest(
    val email: String,
    val password: String,
    val name: String,
    val role: String
)
