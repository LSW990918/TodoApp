package com.example.mytodoapp.domain.todo.dto

data class UpdateTodoRequest(
        val todoTitle: String,
        val todoDescription: String,
        val status: String,
)
