package com.example.mytodoapp.domain.todocard.model

import com.example.mytodoapp.domain.comment.model.Comment
import com.example.mytodoapp.domain.todo.model.Todo
import com.example.mytodoapp.domain.todocard.dto.TodoCardResponse
import jakarta.persistence.*
import java.time.LocalDateTime

var now = LocalDateTime.now()

@Entity
@Table(name = "todocard")
class TodoCard (

        @Column(name = "app_user", nullable = false)
        var app_user: String,

        @Column(name = "password", nullable = false)
        var password: String,

        @Column(name = "date", nullable = false)
        val date: String = "${now.year}-${now.month}-${now.dayOfMonth}",

        @OneToMany(
                cascade = [CascadeType.ALL],
                orphanRemoval=true,
                fetch = FetchType.LAZY)
        @JoinColumn(name = "todocard_id")
        var todoList: MutableList<Todo> = mutableListOf(),

        @OneToMany(
                cascade = [CascadeType.ALL],
                orphanRemoval=true,
                fetch = FetchType.LAZY)
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
                user = app_user,
                date = date
        )
}