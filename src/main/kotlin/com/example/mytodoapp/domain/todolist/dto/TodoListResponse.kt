package com.example.mytodoapp.domain.todolist.dto

import com.example.mytodoapp.domain.todocard.dto.TodoCardResponse

data class TodoListResponse(
        val id: Long,
        val todoTitle: String,
        val todoDescription: String,
        val status: String,
)
