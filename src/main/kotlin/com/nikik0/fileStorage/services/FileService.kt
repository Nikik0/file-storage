package com.nikik0.fileStorage.services

import com.nikik0.fileStorage.dtos.CurrentUserInfo
import com.nikik0.fileStorage.entities.FileEntity
import com.nikik0.fileStorage.repositories.FileRepository
import kotlinx.coroutines.flow.toList
import org.bson.BsonBinarySubType
import org.bson.types.Binary
import org.bson.types.ObjectId
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlow
import reactor.core.publisher.Mono

@Service
class FileService(
    private val fileRepository: FileRepository
) {

    private val baseurl = "http://localhost:8080/api/v1/auth/info"

    private val securityProvider = WebClient.builder().baseUrl(baseurl).build()

    private suspend fun callToCheckCred(authentication: String, acceptedRoles: List<String>): Boolean {
        val currentUserInfo =
            securityProvider.get().header(HttpHeaders.AUTHORIZATION, authentication).retrieve().bodyToFlow<CurrentUserInfo>()
                .toList().first()
        return acceptedRoles.contains(currentUserInfo.role.lowercase())
    }

    suspend fun createFile(file: MultipartFile, auth: String) =
        if (callToCheckCred(auth, listOf("professor", "mentor", "admin")))
            fileRepository.save(
                FileEntity(
                ObjectId().toHexString(),
                Binary(
                    BsonBinarySubType.BINARY,
                    file.bytes
                )
            )
            )
        else Mono.error(RuntimeException())

    suspend fun getSingle(id: String, auth: String) =
        if (callToCheckCred(auth, listOf("professor", "mentor", "admin")))
            fileRepository.findById(id)
        else Mono.error(RuntimeException())

    suspend fun getAll(auth: String) =
        fileRepository.findAll()

}