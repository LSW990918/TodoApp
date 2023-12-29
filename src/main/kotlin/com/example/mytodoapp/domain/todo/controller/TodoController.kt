package com.example.mytodoapp.domain.todo.controller


import com.example.mytodoapp.domain.todo.dto.AddTodoRequest
import com.example.mytodoapp.domain.todo.dto.TodoResponse
import com.example.mytodoapp.domain.todo.dto.UpdateTodoRequest
import com.example.mytodoapp.domain.todo.service.TodoService
import com.example.mytodoapp.domain.todocard.service.TodoCardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todo-cards/{todoCardId}/todos")
@RestController
class TodoController(
        private val todoService: TodoService
) {

    @PostMapping
    fun addTodo(@PathVariable todoCardId: Long, @RequestBody addTodoRequest: AddTodoRequest)
    : ResponseEntity<TodoResponse>{
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoService.addTodo(todoCardId, addTodoRequest))
    }

    @GetMapping
    fun getTodoList(@PathVariable todoCardId: Long): ResponseEntity<List<TodoResponse>>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.getAllTodoList(todoCardId))
    }

    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoCardId: Long,
                @PathVariable todoId: Long): ResponseEntity<TodoResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.getTodoById(todoCardId, todoId))
    }

    @PutMapping("/{todoId}")
    fun updateTodo(
            @PathVariable todoCardId: Long,
            @PathVariable todoId: Long,
            @RequestBody updateTodoRequest: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.updateTodo(todoCardId, todoId, updateTodoRequest))
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(
            @PathVariable todoCardId: Long,
            @PathVariable todoId: Long
    ): ResponseEntity<Unit>{
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(todoService.deleteTodo(todoCardId, todoId))
    }

    @PutMapping("/{todoId}/status")
    fun updateTodoStatus(
            @PathVariable todoCardId: Long,
            @PathVariable todoId: Long,
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.updateTodoStatus(todoCardId, todoId))
    }

}