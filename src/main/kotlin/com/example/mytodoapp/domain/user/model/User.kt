package com.example.mytodoapp.domain.user.model


import com.example.mytodoapp.domain.comment.model.Comment
import com.example.mytodoapp.domain.todocard.model.TodoCard
import com.example.mytodoapp.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(

    @Column(name = "Email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "name")
    var name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: UserRole,

    @OneToMany(
        //mappedBy = "app_user",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val todoCard: MutableList<TodoCard> = mutableListOf(),

    @OneToMany(
        //mappedBy = "app_user",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val comment: MutableList<Comment> = mutableListOf(),

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        name = name,
        email = email,
        role = role.name
    )
}