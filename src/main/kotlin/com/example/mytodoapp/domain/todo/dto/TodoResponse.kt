package com.example.mytodoapp.domain.todo.dto

import com.example.mytodoapp.domain.user.model.User


data class TodoResponse(
    val id: Long,
    val todoTitle: String,
    val todoDescription: String?,
    val status: String,
    //val user: User
)
