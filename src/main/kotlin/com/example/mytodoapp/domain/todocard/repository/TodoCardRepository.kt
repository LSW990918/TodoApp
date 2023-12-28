package com.example.mytodoapp.domain.todocard.repository

import com.example.mytodoapp.domain.todocard.model.TodoCard
import org.springframework.data.jpa.repository.JpaRepository

interface TodoCardRepository: JpaRepository<TodoCard, Long> {
}