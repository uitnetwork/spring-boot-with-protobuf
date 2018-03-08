package com.uitnetwork.demo.controller

import com.uitnetwork.proto.PersonProto.Persons
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.RestTemplate

@RunWith(SpringRunner::class)
@SpringBootTest
class PersonControllerTest {

    @Autowired
    private lateinit var protobufHttpMessageConverter: ProtobufHttpMessageConverter

    private val restTemplate by lazy { RestTemplate(listOf(protobufHttpMessageConverter)) }

    @Test
    fun test() {
        val responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/api/persons", Persons::class.java)

        val persons: Persons = responseEntity.body ?: throw RuntimeException("Response entity does not have Persons")

        assertThat(persons.personCount).isEqualTo(1)
        assertThat(persons.getPerson(0).id).isEqualTo(0)
    }

}
