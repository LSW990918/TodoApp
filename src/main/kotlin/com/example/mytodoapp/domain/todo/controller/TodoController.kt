package com.example.mytodoapp.domain.todo.controller


import com.example.mytodoapp.domain.todo.dto.AddTodoRequest
import com.example.mytodoapp.domain.todo.dto.TodoResponse
import com.example.mytodoapp.domain.todo.dto.UpdateTodoRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todoCards/{todoCardId}/todoList")
@RestController
class TodoListController {

    @PostMapping
    fun createTodo(@RequestBody createTodoCardRequest: AddTodoRequest)
    : ResponseEntity<TodoResponse>{
        TODO()
    }

    @GetMapping
    fun getTodoList(): ResponseEntity<List<TodoResponse>>{
        TODO()
    }

    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoCardId: Long): ResponseEntity<TodoResponse> {
        TODO()
    }

    @PutMapping("/{todoId}")
    fun updateTodo(
            @PathVariable todoCardId: Long,
            @RequestBody updateTodoCardRequest: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        TODO()
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoCardId: Long): ResponseEntity<TodoResponse>{
        TODO()
    }
}