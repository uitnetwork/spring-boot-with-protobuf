package com.uitnetwork.demo.actor

import akka.actor.AbstractActor
import akka.event.Logging
import akka.japi.pf.ReceiveBuilder
import com.uitnetwork.demo.service.PersonService
import com.uitnetwork.proto.PersonProto

@SpringAwareActor
class PersonActor(private val personService: PersonService) : AbstractActor() {
    override fun createReceive(): Receive {
        return ReceiveBuilder.create()
                .match(PersonProto.Person::class.java, this::processPerson)
                .match(String::class.java, this::processString)
                .matchAny(this::processAny)
                .build()
    }

    private val logger = Logging.getLogger(context.system, this)

    private fun processPerson(person: PersonProto.Person) {
        logger.info("Process Person: $person in actor: $this and service: $personService")
    }

    private fun processString(string: String) {
        logger.info("Process String: $string")
    }

    private fun processAny(any: Any) {
        logger.info("Process Any: $any")
    }
}
