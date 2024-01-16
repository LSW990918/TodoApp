package com.example.mytodoapp.domain.user.exception

data class InvalidCredentialException(
    override val message: String? = "The credential is invalid"
) : RuntimeException()