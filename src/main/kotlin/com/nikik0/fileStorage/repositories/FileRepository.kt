package com.nikik0.fileStorage.repositories

import com.nikik0.fileStorage.entities.FileEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface FileRepository: ReactiveMongoRepository<FileEntity, String> {
}