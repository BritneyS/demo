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

    fun updateBookById(id: String, bookRequest: UpdateBookRequestBody): Mono<Book> {
        return getBookById(id)
            .map { oldBook ->
                oldBook
                    .updateAuthor(bookRequest.author)
                    .updateTitle(bookRequest.title)
            }
            .flatMap { updatedBook ->
                saveBook(updatedBook)
            }
    }

    private fun Book.updateAuthor(newBookAuthor: String?): Book {
        return newBookAuthor?.let { this.copy(author = newBookAuthor) } ?: this
    }

    private fun Book.updateTitle(newBookTitle: String?): Book {
        return newBookTitle?.let { this.copy(title = newBookTitle) } ?: this
    }

    private fun saveBook(newBook: Book): Mono<Book> {
        return bookRepository.save(newBook)
    }
}