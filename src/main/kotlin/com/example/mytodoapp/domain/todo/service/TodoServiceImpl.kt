package com.example.mytodoapp.domain.todo.service

import com.example.mytodoapp.domain.todo.dto.AddTodoRequest
import com.example.mytodoapp.domain.todo.dto.TodoResponse
import com.example.mytodoapp.domain.todo.dto.UpdateTodoRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl: TodoService {
    override fun getAllTodoList(todoCardId: Long): List<TodoResponse> {
        TODO("Not yet implemented")
    }

    override fun getTodoById(todoCardId: Long, todoId: Long): TodoResponse {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun addTodo(todoCardId: Long, request: AddTodoRequest): TodoResponse {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateTodo(todoCardId: Long, todoId: Long, request: UpdateTodoRequest): TodoResponse {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteTodo(todoCardId: Long, todoId: Long) {
        TODO("Not yet implemented")
    }
}