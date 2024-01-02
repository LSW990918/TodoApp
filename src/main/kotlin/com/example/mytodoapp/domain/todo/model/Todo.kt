package com.example.mytodoapp.domain.todo.model


import com.example.mytodoapp.domain.todo.dto.TodoResponse
import jakarta.persistence.*


@Entity
@Table(name = "todo")
class Todo(

    @Column(name = "todo_title", nullable = false)
    var todo_title: String,

    @Column(name = "todo_description")
    var todo_description: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: TodoStatus = TodoStatus.INCOMPLETE,

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
}

fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        id = id!!,
        todoTitle = todo_title,
        todoDescription = todo_description,
        status = status.name
    )
}
