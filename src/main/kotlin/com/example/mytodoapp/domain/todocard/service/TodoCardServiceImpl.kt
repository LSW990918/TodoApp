package com.example.mytodoapp.domain.todocard.service

import com.example.mytodoapp.domain.exception.MismatchException
import com.example.mytodoapp.domain.exception.ModelNotFoundException
import com.example.mytodoapp.domain.todocard.dto.TodoCardResponse
import com.example.mytodoapp.domain.todocard.model.TodoCard
import com.example.mytodoapp.domain.todocard.repository.TodoCardRepository
import com.example.mytodoapp.domain.user.repository.UserRepository
import com.example.mytodoapp.infra.aop.StopWatch
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
        if (order == null || order == "ASC") todoCardList.sortedBy { it.date.toLong() }
        if (order == "DESC") todoCardList.sortedByDescending { it.date.toLong() }
        return todoCardList
    }

    @StopWatch
    override fun getTodoCardById(todoCardId: Long): TodoCardResponse {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        return todoCard.toResponse()
    }

    @Transactional
    override fun createTodoCard(userId: Long): TodoCardResponse {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)
        return todoCardRepository.save(
            TodoCard(
                name = user.name,
                user = user,
            )
        ).toResponse()
    }

    @Transactional
    override fun deleteTodoCard(userId: Long, todoCardId: Long) {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        checkCondition(userId, todoCard.user.id!!)
        todoCardRepository.delete(todoCard)
    }
}

fun checkCondition(inputId: Long, existingId: Long) {
    if (inputId != existingId) throw MismatchException(inputId, existingId)
}

fun TodoCard.toResponse(): TodoCardResponse {
    return TodoCardResponse(
        id = id!!,
        name = name,
        date = date,
    )
}