package com.example.mytodoapp.domain.comment.controller



import com.example.mytodoapp.domain.comment.dto.AddCommentRequest
import com.example.mytodoapp.domain.comment.dto.CommentResponse
import com.example.mytodoapp.domain.comment.dto.UpdateCommentRequest
import com.example.mytodoapp.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todo-cards/{todoCardId}/comments")
@RestController
class CommentController(
        private val commentService: CommentService
) {

    @PostMapping
    fun addComment(
            @PathVariable todoCardId: Long,
            @RequestBody addCommentRequest: AddCommentRequest)
    : ResponseEntity<CommentResponse>{
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentService.addComment(todoCardId, addCommentRequest ))
    }

    @GetMapping
    fun getCommentList(@PathVariable todoCardId: Long): ResponseEntity<List<CommentResponse>>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getCommentList(todoCardId))
    }

    @GetMapping("/{commentId}")
    fun getComment(
            @PathVariable todoCardId: Long,
            @PathVariable commentId: Long): ResponseEntity<CommentResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getComment(todoCardId, commentId))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
            @PathVariable todoCardId: Long,
            @PathVariable commentId: Long,
            @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.updateComment(todoCardId, commentId, updateCommentRequest))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
            @PathVariable todoCardId: Long,
            @PathVariable commentId: Long,
            password: String, ): ResponseEntity<Unit>{
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(commentService.deleteComment(todoCardId, commentId, password))
    }
}
