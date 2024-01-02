package com.example.mytodoapp.domain.todocard.service

import com.example.mytodoapp.domain.todocard.dto.CreateTodoCardRequest
import com.example.mytodoapp.domain.todocard.dto.TodoCardResponse
import com.example.mytodoapp.domain.todocard.dto.UpdateTodoCardRequest

interface TodoCardService {
    fun getAllTodoCardList(): List<TodoCardResponse>

    fun getTodoCardById(todoCardId: Long): TodoCardResponse

    fun createTodoCard(request: CreateTodoCardRequest): TodoCardResponse

    fun updateTodoCard(todoCardId: Long, request: UpdateTodoCardRequest): TodoCardResponse

    fun deleteTodoCard(todoCardId: Long, password: String)
}