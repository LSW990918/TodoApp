package com.example.mytodoapp.domain.comment.dto

import com.example.mytodoapp.domain.user.model.User


data class CommentResponse(
    val id: Long,
    val name: String,
    val text: String,
)
