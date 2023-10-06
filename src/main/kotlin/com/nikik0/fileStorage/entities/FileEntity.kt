package com.nikik0.fileStorage.entities

import org.bson.types.Binary
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("files")
data class FileEntity(
    @Id
    val id: String,
    val file: Binary
)
