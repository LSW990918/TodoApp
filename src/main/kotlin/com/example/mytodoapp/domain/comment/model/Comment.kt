package com.example.mytodoapp.domain.comment.model

import com.example.mytodoapp.domain.comment.dto.CommentResponse
import jakarta.persistence.*


@Entity
@Table(name = "comment")
class Comment(

    @Column(name = "app_user", nullable = false)
    var appUser: String,

    @Column(name = "text", nullable = false)
    var text: String,

    @Column(name = "password", nullable = false)
    var password: String,


    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        user = appUser,
        text = text
    )
}