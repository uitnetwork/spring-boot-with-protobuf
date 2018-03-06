package com.uitnetwork.proto

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PersonTest {
    companion object {
        const val TEST_NAME = "TEST_NAME"
    }

    @Test
    fun protoShouldWork() {
        val test = Person.Test.newBuilder().setName(TEST_NAME).build()

        assertThat(test.name).isEqualTo(TEST_NAME)
    }
}
