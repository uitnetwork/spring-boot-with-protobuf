package com.uitnetwork.demo.service

import com.uitnetwork.proto.PersonProto
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class PersonService {
    private val logger = KotlinLogging.logger { }

    fun doSomething(person: PersonProto.Person) {
        logger.info { "Service: $person" }
    }
}
