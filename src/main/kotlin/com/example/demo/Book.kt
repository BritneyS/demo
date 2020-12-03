package com.example.demo

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.UUID

@Document("books")
@TypeAlias("books")
data class Book(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val author: String,
    val title: String,
    @CreatedDate
    val createdOn: Instant = Instant.now(),
    @LastModifiedDate
    val updatedOn: Instant
)
