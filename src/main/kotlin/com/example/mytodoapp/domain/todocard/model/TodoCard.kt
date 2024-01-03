package com.example.mytodoapp.domain.todocard.model

import com.example.mytodoapp.domain.comment.model.Comment
import com.example.mytodoapp.domain.todo.model.Todo
import com.example.mytodoapp.domain.todocard.dto.TodoCardResponse
import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val current = LocalDateTime.now()
val formatter = DateTimeFormatter.ofPattern("yyyy MM dd")


@Entity
@Table(name = "todocard")
class TodoCard(

    @Column(name = "app_user", nullable = false)
    var appUser: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "date", nullable = false)
    val date: String = current.format(formatter),

    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "todocard_id")
    var todoList: MutableList<Todo> = mutableListOf(),

    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "todocard_id")
    var comments: MutableList<Comment> = mutableListOf()


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    fun removeComment(comment: Comment) {
        comments.remove(comment)
    }

    fun addTodo(todo: Todo) {
        todoList.add(todo)
    }

    fun removeTodo(todo: Todo) {
        todoList.remove(todo)
    }
}

fun TodoCard.toResponse(): TodoCardResponse {
    return TodoCardResponse(
        id = id!!,
        user = appUser,
        date = date,
    )
}