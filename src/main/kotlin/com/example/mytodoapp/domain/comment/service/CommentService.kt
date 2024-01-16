package com.example.mytodoapp.domain.comment.service

import com.example.mytodoapp.domain.comment.dto.AddCommentRequest
import com.example.mytodoapp.domain.comment.dto.CommentResponse
import com.example.mytodoapp.domain.comment.dto.UpdateCommentRequest

interface CommentService {

    fun addComment(todoCardId: Long, request: AddCommentRequest): CommentResponse

    fun getCommentList(todoCardId: Long): List<CommentResponse>

    fun getComment(todoCardId: Long, commentId: Long): CommentResponse

    fun updateComment(todoCardId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse

    fun deleteComment(todoCardId: Long, commentId: Long)
}