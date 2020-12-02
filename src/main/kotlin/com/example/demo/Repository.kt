package com.example.demo

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface Repository : ReactiveMongoRepository<Book, String>