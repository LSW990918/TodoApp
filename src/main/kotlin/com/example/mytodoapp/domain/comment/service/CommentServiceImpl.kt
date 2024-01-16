package com.example.mytodoapp.domain.comment.service

import com.example.mytodoapp.domain.comment.dto.AddCommentRequest
import com.example.mytodoapp.domain.comment.dto.CommentResponse
import com.example.mytodoapp.domain.comment.dto.UpdateCommentRequest
import com.example.mytodoapp.domain.comment.model.Comment
import com.example.mytodoapp.domain.comment.model.toResponse
import com.example.mytodoapp.domain.comment.repository.CommentRepository
import com.example.mytodoapp.domain.exception.ModelNotFoundException
import com.example.mytodoapp.domain.todocard.repository.TodoCardRepository
import com.example.mytodoapp.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoCardRepository: TodoCardRepository,
    private val userRepository: UserRepository
) : CommentService {

    @Transactional
    override fun addComment(todoCardId: Long, request: AddCommentRequest): CommentResponse {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val user = userRepository.findByIdOrNull(1)
            ?: throw ModelNotFoundException("User", 1) //추후 수정
        val comment = Comment(
            name = user.name,
            text = request.text,
            user = user,
            todocard = todoCard
            )
        todoCard.addComment(comment)
        todoCardRepository.save(todoCard)
        return comment.toResponse()
    }

    override fun getCommentList(todoCardId: Long): List<CommentResponse> {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        return todoCard.comments.map { it.toResponse() }
    }

    override fun getComment(todoCardId: Long, commentId: Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", commentId)
        return comment.toResponse()
    }

    @Transactional
    override fun updateComment(
        todoCardId: Long,
        commentId: Long,
        request: UpdateCommentRequest
    ): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", commentId)
        comment.text = request.text

        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    override fun deleteComment(todoCardId: Long, commentId: Long) {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId)
            ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", commentId)
        todoCard.removeComment(comment)
        todoCardRepository.save(todoCard)

    }
}