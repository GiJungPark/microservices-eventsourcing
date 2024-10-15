package io.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CartApp

fun main(args: Array<String>) {
    runApplication<CartApp>(*args)
}
