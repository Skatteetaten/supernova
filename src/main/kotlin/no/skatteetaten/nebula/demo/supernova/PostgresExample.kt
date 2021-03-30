package no.skatteetaten.nebula.demo.supernova

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.PostConstruct
import javax.persistence.*


@RestController
class ExampleRestController(val personService: PersonService) {

    @GetMapping("/persons")
    fun getPerson(): List<PersonDto> {
        return personService.getAllPersons()
                .map { personModel -> PersonDto.fromModel(personModel) };
    }
}

data class PersonDto(val name: String) {
    companion object {
        fun fromModel(person: Person) = PersonDto(person.name)
    }
}

@Service
class PersonService(private val personRepository: PersonRepository) {
    fun getAllPersons(): List<Person> = personRepository.findAll()

    @PostConstruct
    fun prepareDatabase() {
        personRepository.deleteAll()
        personRepository.save(Person(name = "Marcus Eisele"))
        personRepository.save(Person(name = "John Doe"))
    }
}

interface PersonRepository : JpaRepository<Person, Number>

@Entity
data class Person(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        @Column(nullable = false)
        val name: String
) {
    constructor() : this(null, "foo") {

    }
}