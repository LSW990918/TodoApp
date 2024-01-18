package com.example.mytodoapp.domain.todo.service

import com.example.mytodoapp.domain.exception.ModelNotFoundException
import com.example.mytodoapp.domain.todo.dto.AddTodoRequest
import com.example.mytodoapp.domain.todo.dto.TodoResponse
import com.example.mytodoapp.domain.todo.dto.UpdateTodoRequest
import com.example.mytodoapp.domain.todo.model.Todo
import com.example.mytodoapp.domain.todo.model.TodoStatus
import com.example.mytodoapp.domain.todo.repository.TodoRepository
import com.example.mytodoapp.domain.todocard.repository.TodoCardRepository
import com.example.mytodoapp.domain.exception.InvalidCredentialException
import com.example.mytodoapp.domain.exception.MismatchException
import com.example.mytodoapp.domain.todocard.service.checkCondition
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
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)
        //userId와 todoCard의 user.id 가 같은지 비교
        checkCondition(userId, todoCard.user.id!!)
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
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        //userId와 todo의 user.id 가 같은지 비교
        checkCondition(user.id!!, todo.user.id!!)
        //todoCardId와 todo의 todoCard.id가 같은지 비교
        checkCondition(todoCard.id!!, todo.todocard.id!!)

        val (todoTitle, todoDescription) = request
        todo.todoTitle = todoTitle
        todo.todoDescription = todoDescription
        //업데이트시에는 세이브가 별도로 필요하지 않음
        //todoRepository.save(todo)
        return todo.toResponse()
    }

    @Transactional
    override fun deleteTodo(userId: Long, todoCardId: Long, todoId: Long) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        //userId와 todo의 user.id 가 같은지 비교
        checkCondition(user.id!!, todo.user.id!!)
        //todoCardId와 todo의 todoCard.id가 같은지 비교
        checkCondition(todoCard.id!!, todo.todocard.id!!)

        todoCard.removeTodo(todo)
        todoCardRepository.save(todoCard)
    }

    override fun updateTodoStatus(
        userId: Long,
        todoCardId: Long,
        todoId: Long
    ): TodoResponse {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        //userId와 todo의 user.id 가 같은지 비교
        checkCondition(user.id!!, todo.user.id!!)
        //todoCardId와 todo의 todoCard.id가 같은지 비교
        checkCondition(todoCard.id!!, todo.todocard.id!!)

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

fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        id = id!!,
        todoTitle = todoTitle,
        todoDescription = todoDescription,
        status = status.name,
    )
}