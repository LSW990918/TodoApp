package com.example.mytodoapp.domain.todocard.controller

import com.example.mytodoapp.domain.todocard.dto.CreateTodoCardRequest
import com.example.mytodoapp.domain.todocard.dto.TodoCardResponse
import com.example.mytodoapp.domain.todocard.dto.UpdateTodoCardRequest
import com.example.mytodoapp.domain.todocard.service.TodoCardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todoCards")
@RestController
class TodoCardController(
        private val todoCardService: TodoCardService
) {

    @PostMapping
    fun createTodoCard(@RequestBody createTodoCardRequest: CreateTodoCardRequest)
    : ResponseEntity<TodoCardResponse>{
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoCardService.createTodoCard(createTodoCardRequest))
    }

    @GetMapping()
    fun getTodoCardList(): ResponseEntity<List<TodoCardResponse>>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoCardService.getAllTodoCardList())
    }

    @GetMapping("/{todoCardId}")
    fun getTodoCard(@PathVariable todoCardId: Long): ResponseEntity<TodoCardResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoCardService.getTodoCardById(todoCardId))
    }

    @PutMapping("/{todoCardId}")
    fun updateTodoCard(
            @PathVariable todoCardId: Long,
            @RequestBody updateTodoCardRequest: UpdateTodoCardRequest
    ): ResponseEntity<TodoCardResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoCardService.updateTodoCard(todoCardId, updateTodoCardRequest))
    }

    @DeleteMapping("/{todoCardId}")
    fun deleteTodoCard(
            @PathVariable todoCardId: Long,
            @PathVariable password: String,
            ): ResponseEntity<Unit>{
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(todoCardService.deleteTodoCard(todoCardId, password))
    }
}