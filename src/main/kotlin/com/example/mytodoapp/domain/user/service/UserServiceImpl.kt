package com.example.mytodoapp.domain.user.service

import com.example.mytodoapp.domain.exception.ModelNotFoundException
import com.example.mytodoapp.domain.user.dto.SignUpRequest
import com.example.mytodoapp.domain.user.dto.UpdateUserProfileRequest
import com.example.mytodoapp.domain.user.dto.UserResponse
import com.example.mytodoapp.domain.user.model.User
import com.example.mytodoapp.domain.user.model.UserRole
import com.example.mytodoapp.domain.user.model.toResponse
import com.example.mytodoapp.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        private val userRepository: UserRepository
): UserService {

    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }

        return userRepository.save(
                User(
                        email = request.email,
                        //TODO: 비밀번호 암호화
                        password = request.password,
                        name =  request.name,
                        role = when (request.role) {
                            UserRole.ADMIN.name -> UserRole.ADMIN
                            UserRole.USER.name -> UserRole.USER
                            else -> throw IllegalArgumentException("Invalid role")
                        }
                )
        ).toResponse()
    }

    @Transactional
    override fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)
        user.name = request.name

        return userRepository.save(user).toResponse()
    }

}