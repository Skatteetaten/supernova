package no.skatteetaten.nebula.demo.supernova.db

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.util.AssertionErrors.assertEquals

@ActiveProfiles("integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AwesomeTestcontainersApplicationTests() {

    @LocalServerPort
    var randomServerPort = 0

    var restTemplate = TestRestTemplate()

    @Test
    fun returnsListOfPersons() {

        val response: ArrayNode = restTemplate.getForObject(createURLWithPort("/persons")) ?: throw Exception("Failed getting persons")

        val expected = """[{"name":"Marcus Eisele"},{"name":"John Doe"}]"""

        val expectedJson: ArrayNode = ObjectMapper().readValue(expected)

        assert(response.size() == 2)
        assertEquals("Should be equal", response, expectedJson)


    }

    private fun createURLWithPort(uri: String): String {
        return "http://localhost:$randomServerPort$uri"
    }
}