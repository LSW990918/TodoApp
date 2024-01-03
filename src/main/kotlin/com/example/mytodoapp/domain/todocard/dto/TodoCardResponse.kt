package com.example.mytodoapp.domain.todocard.dto

import com.example.mytodoapp.domain.todo.dto.TodoResponse


data class TodoCardResponse(
    val id: Long,
    val user: String,
    val date: String,
)
