package com.example.mytodoapp.domain.exception

import com.example.mytodoapp.domain.exception.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@RestController
class GlobalExceptionHandler {

    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse(message = e.message))
    }
    @ExceptionHandler(IncorrectPasswordException::class)
    fun handleModelNotFoundException(e: IncorrectPasswordException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse(message = e.message))
    }
}