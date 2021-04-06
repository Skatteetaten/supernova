package no.skatteetaten.nebula.demo.supernova

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@SpringBootApplication
class SupernovaApplication

fun main(args: Array<String>) {
	runApplication<SupernovaApplication>(*args)
}

@RestController
@RequestMapping("/")
class SuperNovaController() {

    @GetMapping
    fun index() : String {
       logger.info("Request received")
       return "Hello Nebula DEV 11"
    }

}
