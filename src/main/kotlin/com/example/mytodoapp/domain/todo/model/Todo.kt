package com.example.mytodoapp.domain.todo.model

import com.example.mytodoapp.domain.todo.dto.TodoResponse
import jakarta.persistence.*
import kotlin.math.max

@Entity
@Table(name = "todo")
class Todo(

    @Column(name = "todo_title", nullable = false)
    var todoTitle: String,

    @Column(name = "todo_description", nullable = false)
    var todoDescription: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: TodoStatus = TodoStatus.INCOMPLETE,

    @Column(name = "max_title", nullable = false)
    val maxTitle: Int = 200,

    @Column(name = "max_description", nullable = false)
    val maxDescription: Int = 1000,

    @Column(name = "num_title", nullable = false)
    var numTitle: Int = todoTitle.count(),

    @Column(name = "num_description", nullable = false)
    var numDescription: Int = todoDescription.count(),

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun complete() {
        status = TodoStatus.COMPLETE
    }

    fun incomplete() {
        status = TodoStatus.INCOMPLETE
    }

    fun countTitle(): Boolean {
        if (numTitle in 1..maxTitle) {
            return false
        } else return true
    }

    fun countDescription(): Boolean{
        if (numDescription in 1..maxDescription) {
            return false
        } else return true
    }
}

fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        id = id!!,
        todoTitle = todoTitle,
        todoDescription = todoDescription,
        status = status.name,
        maxTitle = maxTitle,
        numTitle = numTitle,
        maxDescription = maxDescription,
        numDescription = numDescription
    )
}
