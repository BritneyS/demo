package com.example.demo

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class Service(
    private val bookRepository: Repository
) {

    fun getBooks(): Flux<Book> {
        return bookRepository.findAll()
    }
}