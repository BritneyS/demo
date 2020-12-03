package com.example.demo

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import java.net.URI

@Component
class Handler(
    private val bookService: Service
) {

    fun getBooks(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(bookService.getBooks(), Book::class.java)

    }

    fun getBookById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id")
        return bookService.getBookById(id)
            .flatMap(ServerResponse.ok()::bodyValue)
    }

    fun createBook(request: ServerRequest): Mono<ServerResponse> {
        return request
            .bodyToMono<CreateBookRequestBody>()
            .flatMap { requestBody ->
                bookService.createBook(requestBody)
            }
            .flatMap { book ->
                ServerResponse
                    .created(URI.create("/books/${book.id}"))
                    .bodyValue(Response(id = book.id))
            }
    }

}

class CreateBookRequestBody(
    val author: String,
    val title: String
)