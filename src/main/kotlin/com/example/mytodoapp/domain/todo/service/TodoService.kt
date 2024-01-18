package com.example.mytodoapp.domain.todo.service

import com.example.mytodoapp.domain.todo.dto.AddTodoRequest
import com.example.mytodoapp.domain.todo.dto.TodoResponse
import com.example.mytodoapp.domain.todo.dto.UpdateTodoRequest

interface TodoService {
    fun getAllTodoList(todoCardId: Long): List<TodoResponse>

    fun getTodoById(todoCardId: Long, todoId: Long): TodoResponse

    fun addTodo(userId: Long, todoCardId: Long, request: AddTodoRequest): TodoResponse

    fun updateTodo(userId: Long, todoCardId: Long, todoId: Long, request: UpdateTodoRequest): TodoResponse

    fun deleteTodo(userId: Long, todoCardId: Long, todoId: Long)

    fun updateTodoStatus(userId: Long, todoCardId: Long, todoId: Long): TodoResponse
}