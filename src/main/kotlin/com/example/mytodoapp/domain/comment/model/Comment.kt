package com.example.mytodoapp.domain.comment.model

import com.example.mytodoapp.domain.comment.dto.CommentResponse
import com.example.mytodoapp.domain.todocard.model.TodoCard
import com.example.mytodoapp.domain.user.model.User
import jakarta.persistence.*


@Entity
@Table(name = "comment")
class Comment(

    @Column(name = "text", nullable = false)
    var text: String,

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @ManyToOne()
    @JoinColumn(name = "todocard_id", nullable = false)
    var todoCard: TodoCard,

    @Column(name = "name", nullable = false)
    var name: String = user.name,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        name = name,
        text = text
    )
}