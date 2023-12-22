package com.example.mytodoapp.domain.todolist.dto

data class TodoResponse(
        val id: Long,
        val todoTitle: String,
        val todoDescription: String,
        val status: String,
)
