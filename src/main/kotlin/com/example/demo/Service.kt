package com.example.demo

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class Service(
    private val bookRepository: Repository
) {

    fun getBooks(): Flux<Book> {
        return bookRepository.findAll()
    }

    fun getBookById(id: String): Mono<Book> {
        return bookRepository.findById(id)
            .switchIfEmpty(Mono.error(Exception("No book found with id $id")))
    }

    fun createBook(bookRequest: CreateBookRequestBody): Mono<Book> {
        return saveBook(Book(author = bookRequest.author, title = bookRequest.title))
    }

    private fun saveBook(newBook: Book): Mono<Book> {
        return bookRepository.save(newBook)
    }
}