package com.uitnetwork.demo.controller

import com.uitnetwork.proto.PersonProto.Person
import com.uitnetwork.proto.PersonProto.Persons
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong
import javax.annotation.PostConstruct

@RestController
@RequestMapping("/api/persons")
class PersonController {
    private val logger = KotlinLogging.logger { }

    private val personMap = ConcurrentHashMap<Long, Person>()
    private val personIdCounter: AtomicLong = AtomicLong(0L)

    @PostConstruct
    fun setup() {
        val fakePerson: Person = Person.newBuilder()
                .setId(0L)
                .setName("fake")
                .setPhone("fake")
                .setAddress("fake")
                .setAge(0)
                .build()

        personMap.put(fakePerson.id, fakePerson)
    }

    @GetMapping
    fun getAllPersons(): Persons {
        val persons = personMap.values
        return Persons.newBuilder()
                .addAllPerson(persons)
                .build()
    }

    @GetMapping("/{id}")
    fun getPerson(@PathVariable("id") id: Long): Person? {
        return personMap.get(id)
    }

    @PostMapping
    fun createPerson(@RequestBody person: Person): Long {
//        Assert.isNull(person.id, "Id must be null") // TODO: how to set Long using protobuf
        val id = personIdCounter.incrementAndGet()
        val creatingPerson: Person = Person.newBuilder()
                .setId(id)
                .setName(person.name)
                .setPhone(person.phone)
                .setAddress(person.address)
                .setAge(person.age)
                .build()

        personMap.put(id, creatingPerson)
        return id
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable("id") id: Long) {
        personMap.remove(id)
    }
}
