package com.example.mytodoapp



import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SpringBootTest
class MyTodoAppApplicationTests() {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy MM dd")
    val date: String = current.format(formatter)

    @Test
    fun main() {

    }
}
