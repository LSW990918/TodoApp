package com.example.mytodoapp.domain.comment.dto


data class AddCommentRequest(
    val user: String,
    val text: String,
    val password: String,
)
