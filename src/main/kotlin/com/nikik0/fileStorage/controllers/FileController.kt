package com.nikik0.fileStorage.controllers
import com.nikik0.fileStorage.entities.FileEntity
import com.nikik0.fileStorage.repositories.FileRepository
import com.nikik0.fileStorage.services.FileService
import org.bson.BsonBinarySubType
import org.bson.types.Binary
import org.bson.types.ObjectId
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux

@RestController
@RequestMapping("/api/v1/storage")
class FileController (
    private val fileService: FileService
        ){

//    @PostMapping(value = ["/save"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
//    suspend fun create(@RequestBody fileEntity: FileEntity) =
//        fileRepository.save(fileEntity).flux()

//    @PostMapping("/save")
//    suspend fun create(@RequestBody fileEntity: FileEntity) =
//        fileRepository.save(fileEntity).flux()

    @PostMapping("/save", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    suspend fun createFile(@RequestParam("file") file: MultipartFile, @RequestHeader("Authorization") auth: String) =
        fileService.createFile(file, auth)

    @GetMapping("/get/{id}")
    suspend fun get(@PathVariable id: String, @RequestHeader("Authorization") auth: String) =
        fileService.getSingle(id, auth)

//    @GetMapping("/test")
//    suspend fun test() =
//        fileRepository.save(FileEntity(
//            ObjectId("10".toByteArray()).toHexString(), Binary(
//                BsonBinarySubType.BINARY,
//                "smin".toByteArray()
//            )
//        ))

    @GetMapping("/all")
    suspend fun getAll(@RequestHeader("Authorization") auth: String) =
        fileService.getAll(auth)
}