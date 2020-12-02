package com.example.demo

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("books")
@TypeAlias("books")
data class Book(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val author: String,
    val title: String
)
