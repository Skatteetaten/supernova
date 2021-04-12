package no.skatteetaten.nebula.demo.supernova

import com.azure.storage.blob.BlobContainerClient
import com.azure.storage.blob.BlobContainerClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*
import java.io.ByteArrayInputStream


@RestController
class Controller(private val azureBlobStorage: AzureBlobStorage) {

    @PutMapping("containers/{containerName}/{blobName}", consumes = ["text/plain"])
    fun postContent(@PathVariable containerName: String, @PathVariable blobName: String, @RequestBody content: String): ResponseEntity<String> {
        azureBlobStorage.createOrGetContainer(containerName).uploadText(blobName, content)
        return ResponseEntity.ok("")
    }

    @GetMapping("containers/{containerName}/{blobName}", produces = ["text/plain"])
    fun getContent(@PathVariable containerName: String, @PathVariable blobName: String) =
            azureBlobStorage.createOrGetContainer(containerName).getText(blobName)
}

@Component
class AzureBlobStorage(
        @Value("\${azure.storage.connectionstring}") private val connectionString: String) {


    fun createOrGetContainer(containerName: String): BlobContainerClient {
        val containerClient = BlobContainerClientBuilder()
                .connectionString(connectionString)
                .containerName(containerName)
                .buildClient()
        if (!containerClient.exists()) {
            containerClient.create()
        }
        return containerClient
    }
}

fun BlobContainerClient.uploadText(blobName: String, text: String) {
    val blobClient = getBlobClient(blobName)
    val content = text.toByteArray()
    blobClient.upload(ByteArrayInputStream(content), content.size.toLong())
}

fun BlobContainerClient.getText(blobName: String) =
        getBlobClient(blobName).openInputStream().use { String(it.readAllBytes()) }