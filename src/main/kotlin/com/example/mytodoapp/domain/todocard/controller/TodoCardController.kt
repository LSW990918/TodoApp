package com.example.mytodoapp.domain.todocard.controller

import com.example.mytodoapp.domain.todocard.dto.TodoCardResponse
import com.example.mytodoapp.domain.todocard.service.TodoCardService
import com.example.mytodoapp.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal

import org.springframework.web.bind.annotation.*

@RequestMapping("/todo-cards")
@RestController
class TodoCardController(
    private val todoCardService: TodoCardService
) {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    fun createTodoCard(
        @AuthenticationPrincipal user: UserPrincipal
    )
            : ResponseEntity<TodoCardResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoCardService.createTodoCard(user.id))
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    fun getTodoCardList(order: String?): ResponseEntity<List<TodoCardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoCardService.getAllTodoCardList(order))
    }

    @GetMapping("/{todoCardId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    fun getTodoCardById(@PathVariable todoCardId: Long): ResponseEntity<TodoCardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoCardService.getTodoCardById(todoCardId))
    }

    @DeleteMapping("/{todoCardId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    fun deleteTodoCard(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable todoCardId: Long,
        password: String,
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(todoCardService.deleteTodoCard(user.id, todoCardId))
    }
}