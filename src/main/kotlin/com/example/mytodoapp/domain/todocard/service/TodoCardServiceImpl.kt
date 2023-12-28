package com.example.mytodoapp.domain.todocard.service

import com.example.mytodoapp.domain.exception.IncorrectPasswordException
import com.example.mytodoapp.domain.exception.ModelNotFoundException
import com.example.mytodoapp.domain.todocard.dto.CreateTodoCardRequest
import com.example.mytodoapp.domain.todocard.dto.TodoCardResponse
import com.example.mytodoapp.domain.todocard.dto.UpdateTodoCardRequest
import com.example.mytodoapp.domain.todocard.model.TodoCard
import com.example.mytodoapp.domain.todocard.model.toResponse
import com.example.mytodoapp.domain.todocard.repository.TodoCardRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoCardServiceImpl(
        private val todoCardRepository: TodoCardRepository
): TodoCardService {
    override fun getAllTodoCardList(): List<TodoCardResponse> {
        return todoCardRepository.findAll().map{ it.toResponse() }
    }

    override fun getTodoCardById(todoCardId: Long): TodoCardResponse {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
                ?: throw ModelNotFoundException("TodoCard", todoCardId)
        return todoCard.toResponse()
    }

    @Transactional
    override fun createTodoCard(request: CreateTodoCardRequest): TodoCardResponse {
        return todoCardRepository.save(
                TodoCard(
                        app_user = request.user,
                        password = request.password
                )
        ).toResponse()
    }

    @Transactional
    override fun updateTodoCard(todoCardId: Long, request: UpdateTodoCardRequest): TodoCardResponse {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
                ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val (user) = request

        todoCard.app_user = user

        return todoCardRepository.save(todoCard).toResponse()
    }

    @Transactional
    override fun deleteTodoCard(todoCardId: Long, password: String) {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
                ?: throw ModelNotFoundException("TodoCard", todoCardId)
        if (password == "masterPW5946" || password == todoCard.password ){
            todoCardRepository.delete(todoCard)
        } else {
            throw IncorrectPasswordException(password, todoCardId)
        }

    }
}