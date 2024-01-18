package com.example.mytodoapp.domain.todo.model

import com.example.mytodoapp.domain.todo.dto.TodoResponse
import com.example.mytodoapp.domain.todocard.model.TodoCard
import com.example.mytodoapp.domain.user.model.User
import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "todo")
class Todo(

    @Column(name = "todo_title", nullable = false)
    @Size(min = 1, max = 200)
    var todoTitle: String,

    @Column(name = "todo_description", nullable = false)
    @Size(min = 1, max = 1000)
    var todoDescription: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: TodoStatus = TodoStatus.INCOMPLETE,

    @ManyToOne()
    @JoinColumn(name = "todocard_id", nullable = false)
    var todocard: TodoCard,

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

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
        todoTitle = todoTitle,
        todoDescription = todoDescription,
        status = status.name,
        //user = user
    )
}
