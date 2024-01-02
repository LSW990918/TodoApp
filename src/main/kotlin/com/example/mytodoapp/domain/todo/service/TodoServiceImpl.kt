package com.example.mytodoapp.domain.todo.service

import com.example.mytodoapp.domain.exception.ModelNotFoundException
import com.example.mytodoapp.domain.todo.dto.AddTodoRequest
import com.example.mytodoapp.domain.todo.dto.TodoResponse
import com.example.mytodoapp.domain.todo.dto.UpdateTodoRequest
import com.example.mytodoapp.domain.todo.model.Todo
import com.example.mytodoapp.domain.todo.model.TodoStatus
import com.example.mytodoapp.domain.todo.model.toResponse
import com.example.mytodoapp.domain.todo.repository.TodoRepository
import com.example.mytodoapp.domain.todocard.repository.TodoCardRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,
    private val todoCardRepository: TodoCardRepository
) : TodoService {
    override fun getAllTodoList(todoCardId: Long): List<TodoResponse> {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        return todoCard.todoList.map { it.toResponse() }
    }

    override fun getTodoById(todoCardId: Long, todoId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        return todo.toResponse()
    }

    @Transactional
    override fun addTodo(todoCardId: Long, request: AddTodoRequest): TodoResponse {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val todo = Todo(
            todo_title = request.todoTitle,
            todo_description = request.todoDescription
        )
        todoCard.addTodo(todo)
        todoCardRepository.save(todoCard)
        return todo.toResponse()
    }

    @Transactional
    override fun updateTodo(
        todoCardId: Long,
        todoId: Long,
        request: UpdateTodoRequest
    ): TodoResponse {

        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("Todo", todoId)

        val (todoTitle, todoDescription) = request
        todo.todo_title = todoTitle
        todo.todo_description = todoDescription

        return todoRepository.save(todo).toResponse()
    }

    @Transactional
    override fun deleteTodo(todoCardId: Long, todoId: Long) {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        todoCard.removeTodo(todo)
        todoCardRepository.save(todoCard)
    }

    override fun updateTodoStatus(todoCardId: Long, todoId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        while (true) {
            if (todo.status == TodoStatus.INCOMPLETE) {
                todo.complete()
                break
            }
            if (todo.status == TodoStatus.COMPLETE) {
                todo.incomplete()
                break
            }
        }
        return todoRepository.save(todo).toResponse()
    }

}