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
) : TodoCardService {
    override fun getAllTodoCardList(order: String?, name: String?): List<TodoCardResponse> {
        val todoCardList = todoCardRepository.findAll().map { it.toResponse() }
        if (order == null || order == "ASC") {
            todoCardList.sortedBy { it.date.toLong() }
        } else if (order == "DESC") {
            todoCardList.sortedByDescending { it.date.toLong() }
        }
        if (name != null) {
            return todoCardList.filter { it.user == name }
        }
        return todoCardList
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
                appUser = request.user,
                password = request.password
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodoCard(todoCardId: Long, request: UpdateTodoCardRequest): TodoCardResponse {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val (user) = request

        todoCard.appUser = user

        return todoCardRepository.save(todoCard).toResponse()
    }

    @Transactional
    override fun deleteTodoCard(todoCardId: Long, password: String) {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val masterPw = "1324"
        if (password == masterPw || password == todoCard.password) {
            todoCardRepository.delete(todoCard)
        } else {
            throw IncorrectPasswordException(password, todoCardId)
        }

    }
}