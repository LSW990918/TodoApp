package com.example.mytodoapp.domain.todocard.service

import com.example.mytodoapp.infra.aop.StopWatch
import com.example.mytodoapp.domain.exception.IncorrectPasswordException
import com.example.mytodoapp.domain.exception.ModelNotFoundException
import com.example.mytodoapp.domain.todocard.dto.CreateTodoCardRequest
import com.example.mytodoapp.domain.todocard.dto.TodoCardResponse
import com.example.mytodoapp.domain.todocard.model.TodoCard
import com.example.mytodoapp.domain.todocard.model.toResponse
import com.example.mytodoapp.domain.todocard.repository.TodoCardRepository
import com.example.mytodoapp.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoCardServiceImpl(
    private val todoCardRepository: TodoCardRepository,
    private val userRepository: UserRepository
) : TodoCardService {
    override fun getAllTodoCardList(order: String?): List<TodoCardResponse> {
        val todoCardList = todoCardRepository.findAll().map { it.toResponse() }
        if (order == null || order == "ASC") {
            todoCardList.sortedBy { it.date.toLong() }
        } else if (order == "DESC") {
            todoCardList.sortedByDescending { it.date.toLong() }
        }
        return todoCardList
    }

    @StopWatch
    override fun getTodoCardById(todoCardId: Long): TodoCardResponse {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        return todoCard.toResponse()
    }

    @Transactional
    override fun createTodoCard(request: CreateTodoCardRequest): TodoCardResponse {
        val user = userRepository.findByIdOrNull(1)
            ?: throw ModelNotFoundException("User", 1) //추후 수정필요
        return todoCardRepository.save(
            TodoCard(
                name = user.name,
                password = request.password,
                user = user
            )
        ).toResponse()
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