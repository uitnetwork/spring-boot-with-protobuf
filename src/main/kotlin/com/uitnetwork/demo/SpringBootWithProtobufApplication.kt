package com.uitnetwork.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootWithProtobufApplication

fun main(args: Array<String>) {
    runApplication<SpringBootWithProtobufApplication>(*args)
}
