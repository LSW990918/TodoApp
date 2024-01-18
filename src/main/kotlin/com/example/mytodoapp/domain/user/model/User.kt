package com.example.mytodoapp.domain.user.model


import com.example.mytodoapp.domain.comment.model.Comment
import com.example.mytodoapp.domain.todocard.model.TodoCard
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
        mappedBy = "user",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val todocard: MutableList<TodoCard> = mutableListOf(),

    @OneToMany(
        mappedBy = "user",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val comment: MutableList<Comment> = mutableListOf(),

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}