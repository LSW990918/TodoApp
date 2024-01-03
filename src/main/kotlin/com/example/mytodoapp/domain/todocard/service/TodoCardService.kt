package com.example.mytodoapp.domain.todocard.service

import com.example.mytodoapp.domain.todocard.dto.CreateTodoCardRequest
import com.example.mytodoapp.domain.todocard.dto.TodoCardResponse
import com.example.mytodoapp.domain.todocard.dto.UpdateTodoCardRequest

interface TodoCardService {
    fun getAllTodoCardList(order:String?, name: String?): List<TodoCardResponse>

    fun getTodoCardById(todoCardId: Long): TodoCardResponse

    fun createTodoCard(request: CreateTodoCardRequest): TodoCardResponse

    fun updateTodoCard(todoCardId: Long, request: UpdateTodoCardRequest): TodoCardResponse

    fun deleteTodoCard(todoCardId: Long, password: String)
}