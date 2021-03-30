package no.skatteetaten.nebula.demo.supernova.db

import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AwesomeTestcontainersApplicationTests() {

    @LocalServerPort
    var randomServerPort = 0

    var restTemplate = TestRestTemplate()
    var headers: HttpHeaders = HttpHeaders()

    @Test
    fun returnsListOfPersons() {
        val entity = HttpEntity<String>(null, headers)

        val response = restTemplate.exchange(
                createURLWithPort("/persons"),
                HttpMethod.GET, entity, String::class.java)

        val expected = "[{name:\"Marcus Eisele\"},{name:\"John Doe\"}]"

        JSONAssert.assertEquals(expected, response.body, false)
    }

    private fun createURLWithPort(uri: String): String {
        return "http://localhost:$randomServerPort$uri"
    }
}