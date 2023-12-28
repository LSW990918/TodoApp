package com.example.mytodoapp.domain.comment.repository

import com.example.mytodoapp.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository <Comment, Long> {
}