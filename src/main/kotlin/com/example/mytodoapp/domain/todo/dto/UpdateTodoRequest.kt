package com.example.mytodoapp.domain.todolist.dto

data class UpdateTodoListRequest(
        val todoTitle: String,
        val todoDescription: String,
)
