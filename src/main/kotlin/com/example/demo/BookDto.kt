package com.example.demo

class BookDto (
    private val book: Book
) {
    val id = book.id
    val author = book.author
    val title = book.title
}