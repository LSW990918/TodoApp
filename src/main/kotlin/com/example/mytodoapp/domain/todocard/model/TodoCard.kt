package com.example.mytodoapp.domain.todocard.model

import com.example.mytodoapp.domain.comment.model.Comment
import com.example.mytodoapp.domain.todo.model.Todo
import com.example.mytodoapp.domain.todocard.dto.TodoCardResponse
import com.example.mytodoapp.domain.user.model.User
import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val current = LocalDateTime.now()
val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")


@Entity
@Table(name = "todocard")
class TodoCard(

    @Column(name = "date", nullable = false)
    val date: String = current.format(formatter),

    @OneToMany(
        mappedBy = "todocard",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    var todoList: MutableList<Todo> = mutableListOf(),

    @OneToMany(
        mappedBy = "todocard",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    var comments: MutableList<Comment> = mutableListOf(),

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @Column(name = "name", nullable = false)
    var name: String,


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