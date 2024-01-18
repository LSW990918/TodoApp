package com.example.mytodoapp.domain.todo.controller


import com.example.mytodoapp.domain.todo.dto.AddTodoRequest
import com.example.mytodoapp.domain.todo.dto.TodoResponse
import com.example.mytodoapp.domain.todo.dto.UpdateTodoRequest
import com.example.mytodoapp.domain.todo.service.TodoService
import com.example.mytodoapp.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/todo-cards/{todoCardId}/todos")
@RestController
class TodoController(
    private val todoService: TodoService
) {

    @PostMapping
    fun addTodo(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable todoCardId: Long,
        @RequestBody addTodoRequest: AddTodoRequest
    )
            : ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.addTodo(user.id, todoCardId, addTodoRequest))
    }

    @GetMapping
    fun getTodoList(@PathVariable todoCardId: Long): ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getAllTodoList(todoCardId))
    }

    @GetMapping("/{todoId}")
    fun getTodo(
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoById(todoCardId, todoId))
    }

    @PutMapping("/{todoId}")
    fun updateTodo(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long,
        @RequestBody updateTodoRequest: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodo(user.id, todoCardId, todoId, updateTodoRequest))
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(todoService.deleteTodo(user.id, todoCardId, todoId))
    }

    @PutMapping("/{todoId}/status")
    fun updateTodoStatus(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long,
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodoStatus(user.id, todoCardId, todoId))
    }

}