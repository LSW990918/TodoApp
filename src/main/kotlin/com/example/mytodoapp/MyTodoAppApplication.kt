package com.example.mytodoapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class MyTodoAppApplication

fun main(args: Array<String>) {
    runApplication<MyTodoAppApplication>(*args)
}