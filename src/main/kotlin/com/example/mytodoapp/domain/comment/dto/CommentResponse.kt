package com.example.mytodoapp.domain.comment.dto


data class CommentResponse(
    val id: Long,
    val user: String,
    val text: String,
)
