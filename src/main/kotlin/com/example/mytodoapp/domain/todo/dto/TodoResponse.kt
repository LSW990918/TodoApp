package com.example.mytodoapp.domain.todo.dto

data class TodoResponse(
        val id: Long,
        val todoTitle: String,
        val todoDescription: String,
        val status: String,
)
