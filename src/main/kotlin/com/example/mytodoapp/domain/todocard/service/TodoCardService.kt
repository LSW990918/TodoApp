package com.example.mytodoapp.domain.todocard.service

import com.example.mytodoapp.domain.todocard.dto.TodoCardResponse

interface TodoCardService {
    fun getAllTodoCardList(order: String?): List<TodoCardResponse>

    fun getTodoCardById(todoCardId: Long): TodoCardResponse

    fun createTodoCard(userId: Long): TodoCardResponse

    fun deleteTodoCard(userId: Long, todoCardId: Long)
}