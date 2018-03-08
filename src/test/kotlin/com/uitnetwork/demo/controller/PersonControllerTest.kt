package com.uitnetwork.demo.controller


import com.googlecode.protobuf.format.JsonFormat
import com.uitnetwork.proto.PersonProto.Persons
import mu.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.RestTemplate

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class PersonControllerTest {
    private val logger = KotlinLogging.logger { }

    @Autowired
    private lateinit var protobufHttpMessageConverter: ProtobufHttpMessageConverter

    @LocalServerPort
    private val localServerPort: Int = 0

    private val restTemplate by lazy { RestTemplate(listOf(protobufHttpMessageConverter)) }

    private val jsonFormat = JsonFormat()

    @Test
    fun test() {
        val responseEntity = restTemplate.getForEntity("http://127.0.0.1:$localServerPort/api/persons", Persons::class.java)

        val persons: Persons = responseEntity.body ?: throw RuntimeException("Response entity does not have Persons")

        assertThat(persons.personCount).isEqualTo(1)
        assertThat(persons.getPerson(0).id).isEqualTo(0)

        logger.info { "JSON of Persons object: ${jsonFormat.printToString(persons)}" }
    }
}
