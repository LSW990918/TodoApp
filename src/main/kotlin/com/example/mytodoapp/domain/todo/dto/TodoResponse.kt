package com.example.mytodoapp.domain.todo.dto

data class TodoResponse(
    val id: Long,
    val todoTitle: String,
    val todoDescription: String?,
    val status: String,
    val maxTitle: Int,
    val numTitle: Int,
    val maxDescription: Int,
    val numDescription: Int,
)
