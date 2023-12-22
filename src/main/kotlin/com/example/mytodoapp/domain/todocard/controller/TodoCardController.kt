package com.example.mytodoapp.domain.todocard.controller

import com.example.mytodoapp.domain.todocard.dto.CreateTodoCardRequest
import com.example.mytodoapp.domain.todocard.dto.TodoCardResponse
import com.example.mytodoapp.domain.todocard.dto.UpdateTodoCardRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todoCards")
@RestController
class TodoCardController {

    @PostMapping
    fun createTodoCard(@RequestBody createTodoCardRequest: CreateTodoCardRequest)
    : ResponseEntity<TodoCardResponse>{
        TODO()
    }

    @GetMapping
    fun getTodoCardList(): ResponseEntity<List<TodoCardResponse>>{
        TODO()
    }

    @GetMapping("/{todoCardId}")
    fun getTodoCard(@PathVariable todoCardId: Long): ResponseEntity<TodoCardResponse> {
        TODO()
    }

    @PutMapping("/{todoCardId}")
    fun updateCourse(
            @PathVariable todoCardId: Long,
            @RequestBody updateTodoCardRequest: UpdateTodoCardRequest
    ): ResponseEntity<TodoCardResponse> {
        TODO()
    }

    @DeleteMapping("/{todoCardId}")
    fun deleteTodoCard(@PathVariable todoCardId: Long): ResponseEntity<TodoCardResponse>{
        TODO()
    }
}