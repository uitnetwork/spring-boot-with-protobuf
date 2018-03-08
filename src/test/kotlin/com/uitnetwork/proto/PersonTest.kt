package com.uitnetwork.proto

import com.uitnetwork.proto.PersonProto.Person
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PersonTest {
    companion object {
        const val TEST_NAME = "TEST_NAME"
        const val TEST_ADDRESS = "TEST_ADDRESS"
        const val TEST_PHONE = "12345678"
        const val TEST_AGE = 80
    }

    @Test
    fun personProtoShouldWork() {
        val test: Person = Person.newBuilder()
                .setName(TEST_NAME)
                .setAddress(TEST_ADDRESS)
                .setPhone(TEST_PHONE)
                .setAge(TEST_AGE)
                .build()

        assertThat(test.name).isEqualTo(TEST_NAME)
        assertThat(test.address).isEqualTo(TEST_ADDRESS)
        assertThat(test.phone).isEqualTo(TEST_PHONE)
        assertThat(test.age).isEqualTo(TEST_AGE)
    }
}
