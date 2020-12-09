package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class Routes(
    private val bookHandler: Handler
) {
    @Bean
    fun bookRouter(): RouterFunction<ServerResponse> = router {
        GET("/books", bookHandler::getBooks)
        GET("/books/{id}", bookHandler::getBookById)
        POST("/books", bookHandler::createBook)
        PATCH("books/{id}", bookHandler::updateBookById)
    }
}