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
import com.example.mytodoapp.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,
    private val todoCardRepository: TodoCardRepository,
    private val userRepository: UserRepository
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
    override fun addTodo(userId: Long, todoCardId: Long, request: AddTodoRequest): TodoResponse {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val user = userRepository.findByIdOrNull(1)
            ?: throw ModelNotFoundException("User", 1) //추후 수정
        val todo = Todo(
            todoTitle = request.todoTitle,
            todoDescription = request.todoDescription,
            todocard = todoCard,
            user = user
        )
        todoCard.addTodo(todo)
        todoCardRepository.save(todoCard)
        return todo.toResponse()
    }

    @Transactional
    override fun updateTodo(
        userId: Long,
        todoCardId: Long,
        todoId: Long,
        request: UpdateTodoRequest
    ): TodoResponse {

        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("Todo", todoId)

        val (todoTitle, todoDescription) = request
        todo.todoTitle = todoTitle
        todo.todoDescription = todoDescription
        return todoRepository.save(todo).toResponse()
    }

    @Transactional
    override fun deleteTodo(userId: Long, todoCardId: Long, todoId: Long) {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        todoCard.removeTodo(todo)
        todoCardRepository.save(todoCard)
    }

    override fun updateTodoStatus(
        userId: Long,
        todoCardId: Long,
        todoId: Long
    ): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("Todo", todoId)

        if (todo.status == TodoStatus.INCOMPLETE) {
            todo.complete()
            return todoRepository.save(todo).toResponse()
        }
        if (todo.status == TodoStatus.COMPLETE) {
            todo.incomplete()
            return todoRepository.save(todo).toResponse()
        }
        return todo.toResponse()
    }

}