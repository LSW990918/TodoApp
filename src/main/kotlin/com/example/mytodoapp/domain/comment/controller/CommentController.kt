package com.example.mytodoapp.domain.comment.controller


import com.example.mytodoapp.domain.comment.dto.AddCommentRequest
import com.example.mytodoapp.domain.comment.dto.CommentResponse
import com.example.mytodoapp.domain.comment.dto.UpdateCommentRequest
import com.example.mytodoapp.domain.comment.service.CommentService
import com.example.mytodoapp.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/todo-cards/{todoCardId}/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping
    fun addComment(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable todoCardId: Long,
        @RequestBody addCommentRequest: AddCommentRequest
    )
            : ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.addComment(user.id, todoCardId, addCommentRequest))
    }

    @GetMapping
    fun getCommentList(@PathVariable todoCardId: Long): ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentList(todoCardId))
    }

    @GetMapping("/{commentId}")
    fun getComment(
        @PathVariable todoCardId: Long,
        @PathVariable commentId: Long
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getComment(todoCardId, commentId))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable todoCardId: Long,
        @PathVariable commentId: Long,
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(user.id, todoCardId, commentId, updateCommentRequest))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable todoCardId: Long,
        @PathVariable commentId: Long,
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(commentService.deleteComment(user.id, todoCardId, commentId))
    }
}
