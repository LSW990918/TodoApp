package com.example.mytodoapp.domain.user.service

import com.example.mytodoapp.domain.exception.ModelNotFoundException
import com.example.mytodoapp.domain.user.dto.*
import com.example.mytodoapp.domain.user.exception.InvalidCredentialException
import com.example.mytodoapp.domain.user.model.User
import com.example.mytodoapp.domain.user.model.UserRole
import com.example.mytodoapp.domain.user.model.toResponse
import com.example.mytodoapp.domain.user.repository.UserRepository
import com.example.mytodoapp.infra.security.jwt.JwtPlugin
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : UserService {

    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw ModelNotFoundException("User", null)

        if (user.role.name != request.role || !passwordEncoder.matches(request.password, user.password)) {
            throw InvalidCredentialException()
        }
        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.name
            )
        )
    }

    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }

        return userRepository.save(
            User(
                email = request.email,
                password = passwordEncoder.encode(request.password), //비밀번호 암호화
                name = request.name,
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