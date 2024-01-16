package com.example.mytodoapp.domain.user.service

import com.example.mytodoapp.domain.user.dto.*

interface UserService {

    fun login(request: LoginRequest): LoginResponse

    fun signUp(request: SignUpRequest): UserResponse

    fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse
}