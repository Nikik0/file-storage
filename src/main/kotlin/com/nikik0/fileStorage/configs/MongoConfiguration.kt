package com.nikik0.fileStorage.configs

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories


@Configuration
@EnableReactiveMongoRepositories
class MongoConfiguration: AbstractReactiveMongoConfiguration() {
//    @Bean
//    fun mongoClient(): MongoClient? {
//        return MongoClients.create()
//    }

    @Bean
    override fun reactiveMongoClient(): MongoClient {
        return MongoClients.create("mongodb://user:123@localhost:27017/file_db")
    }

    override fun getDatabaseName(): String =
        "file_db"
}