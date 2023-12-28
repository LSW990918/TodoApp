package com.example.mytodoapp.domain.exception

data class IncorrectPasswordException(val password: String, val id: Long?):
        RuntimeException("password:$password does not match with given password of id: $id")
