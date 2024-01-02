package com.example.mytodoapp.domain.todo.repository

import com.example.mytodoapp.domain.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
}