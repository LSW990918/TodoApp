package com.example.mytodoapp.domain.comment.dto

data class UpdateCommentRequest(
        val user: String,
        val text: String,
        val password: Long,
)
