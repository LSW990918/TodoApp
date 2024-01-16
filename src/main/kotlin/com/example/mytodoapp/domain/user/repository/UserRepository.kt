package com.example.mytodoapp.domain.user.repository

import com.example.mytodoapp.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun existsByEmail(email: String): Boolean
}
