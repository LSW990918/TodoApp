package com.example.mytodoapp.domain.user.dto

data class UserResponse(
    val id: Long,
    val email: String,
    val name: String,
    val role: String
)
