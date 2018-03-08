package com.uitnetwork.proto

import com.uitnetwork.proto.PersonProto.Person
import com.uitnetwork.proto.PersonProto.Persons
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PersonTest {
    companion object {
        const val TEST_ID = 1L
        const val TEST_NAME = "TEST_NAME"
        const val TEST_ADDRESS = "TEST_ADDRESS"
        const val TEST_PHONE = "12345678"
        const val TEST_AGE = 80
    }

    @Test
    fun personProtoShouldWork() {
        val test: Person = Person.newBuilder()
                .setId(TEST_ID)
                .setName(TEST_NAME)
                .setAddress(TEST_ADDRESS)
                .setPhone(TEST_PHONE)
                .setAge(TEST_AGE)
                .build()

        assertThat(test.id).isEqualTo(TEST_ID)
        assertThat(test.name).isEqualTo(TEST_NAME)
        assertThat(test.address).isEqualTo(TEST_ADDRESS)
        assertThat(test.phone).isEqualTo(TEST_PHONE)
        assertThat(test.age).isEqualTo(TEST_AGE)
    }

    @Test
    fun personsProtoShouldWork() {
        val persons = Persons.newBuilder()
                .addAllPerson(listOf(Person.newBuilder().setName(TEST_NAME).build()))
                .build()

        assertThat(persons.personCount).isEqualTo(1)
        assertThat(persons.getPerson(0).name).isEqualTo(TEST_NAME)
    }
}
