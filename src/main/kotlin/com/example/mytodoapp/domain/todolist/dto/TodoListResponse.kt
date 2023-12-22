package com.example.mytodoapp.domain.todolist.dto

data class TodoListResponse(
        val id: Long,
        val todoTitle: String,
        val todoDescription: String,
        val status: String,
)
