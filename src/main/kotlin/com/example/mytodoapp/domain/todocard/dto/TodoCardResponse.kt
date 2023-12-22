package com.example.mytodoapp.domain.todocard.dto

import com.example.mytodoapp.domain.todolist.dto.TodoListResponse


data class TodoCardResponse(
        val id: Long,
        val user: String,
        val todoList: TodoListResponse?,
        val date: String,
)
