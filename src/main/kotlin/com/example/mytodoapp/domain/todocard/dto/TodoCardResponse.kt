package com.example.mytodoapp.domain.todocard.dto

import com.example.mytodoapp.domain.user.model.User


data class TodoCardResponse(
    val id: Long,
    val name: String,
    val date: String,
)
