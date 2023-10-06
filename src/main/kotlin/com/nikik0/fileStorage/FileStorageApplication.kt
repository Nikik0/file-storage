package com.nikik0.fileStorage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
@EnableReactiveMongoRepositories
class FileStorageApplication

fun main(args: Array<String>) {
	runApplication<FileStorageApplication>(*args)
}
