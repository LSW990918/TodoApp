package com.example.mytodoapp.domain.user.service

import com.example.mytodoapp.domain.user.dto.SignUpRequest
import com.example.mytodoapp.domain.user.dto.UpdateUserProfileRequest
import com.example.mytodoapp.domain.user.dto.UserResponse

interface UserService {

    fun signUp(request: SignUpRequest): UserResponse

    fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse
}