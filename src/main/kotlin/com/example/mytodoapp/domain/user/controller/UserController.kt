package com.example.mytodoapp.domain.user.controller


import com.example.mytodoapp.domain.user.dto.SignUpRequest
import com.example.mytodoapp.domain.user.dto.UpdateUserProfileRequest
import com.example.mytodoapp.domain.user.dto.UserResponse
import com.example.mytodoapp.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
        private val userService: UserService
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.signUp(signUpRequest))
    }

    @PutMapping("/users/{userId}/profile")
    fun updateUserProfile(@PathVariable userId: Long,
                          @RequestBody updateUserProfileRequest: UpdateUserProfileRequest
    ): ResponseEntity<UserResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateUserProfile(userId, updateUserProfileRequest))
    }
}