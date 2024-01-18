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

    @ExceptionHandler(InvalidCredentialException::class)
    fun handleInvalidCredentialException(e: InvalidCredentialException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(MismatchException::class)
    fun handleMismatchExceptionException(e: MismatchException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.PRECONDITION_FAILED)//알맞는 status인지 잘 모르겠음
            .body(ErrorResponse(message = e.message))
    }
}