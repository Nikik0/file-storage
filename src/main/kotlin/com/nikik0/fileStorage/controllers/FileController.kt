package com.nikik0.fileStorage.controllers
import com.nikik0.fileStorage.entities.FileEntity
import com.nikik0.fileStorage.repositories.FileRepository
import org.bson.BsonBinarySubType
import org.bson.types.Binary
import org.bson.types.ObjectId
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toFlux

@RestController
@RequestMapping("/api/v1/storage")
class FileController (
    private val fileRepository: FileRepository
        ){

//    @PostMapping(value = ["/save"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
//    suspend fun create(@RequestBody fileEntity: FileEntity) =
//        fileRepository.save(fileEntity).flux()

//    @PostMapping("/save")
//    suspend fun create(@RequestBody fileEntity: FileEntity) =
//        fileRepository.save(fileEntity).flux()

    @PostMapping("/save", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    suspend fun createFile(@RequestParam("file") file: MultipartFile): Flux<FileEntity> {
        println(ObjectId())
        return fileRepository.save(FileEntity(
            ObjectId().toHexString(),
            Binary(
                BsonBinarySubType.BINARY,
                file.bytes
            )
        )).flux()
    }

    @GetMapping("/get/{id}")
    suspend fun get(@PathVariable id: String) =
        fileRepository.findById(id).flux()

    @GetMapping("/test")
    suspend fun test() =
        fileRepository.save(FileEntity(
            ObjectId("10".toByteArray()).toHexString(), Binary(
                BsonBinarySubType.BINARY,
                "smin".toByteArray()
            )
        )).flux()

    @GetMapping("/all")
    suspend fun getAll() =
        fileRepository.findAll()
}