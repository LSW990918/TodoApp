package com.example.mytodoapp.domain.comment.controller



import com.example.mytodoapp.domain.comment.dto.AddCommentRequest
import com.example.mytodoapp.domain.comment.dto.CommentResponse
import com.example.mytodoapp.domain.comment.dto.UpdateCommentRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todoCards/{todoCardId}/comment")
@RestController
class CommentController {

    @PostMapping
    fun createComment(
            @PathVariable todoCardId: Long,
            @RequestBody createTodoCardRequest: AddCommentRequest)
    : ResponseEntity<CommentResponse>{
        TODO()
    }

    @GetMapping
    fun getCommentList(@PathVariable todoCardId: Long): ResponseEntity<List<CommentResponse>>{
        TODO()
    }

    @GetMapping("/{commentId}")
    fun getComment(
            @PathVariable todoCardId: Long,
            @PathVariable commentId: Long): ResponseEntity<CommentResponse> {
        TODO()
    }

    @PutMapping("/{commentId}")
    fun updateComment(
            @PathVariable todoCardId: Long,
            @PathVariable commentId: Long,
            @RequestBody updateTodoCardRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        TODO()
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
            @PathVariable todoCardId: Long,
            @PathVariable commentId: Long,
            @PathVariable password: String, ): ResponseEntity<CommentResponse>{
        TODO()
    }
}