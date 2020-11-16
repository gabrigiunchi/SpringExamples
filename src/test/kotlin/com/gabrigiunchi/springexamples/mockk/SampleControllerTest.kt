package com.gabrigiunchi.springexamples.mockk

import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.spyk
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(MockKExtension::class)
@AutoConfigureMockMvc
@SpringBootTest
class SampleControllerTest {

    @Autowired
    private lateinit var mockMv: MockMvc

    @Autowired
    private lateinit var sampleService: SampleService

    @SpringBootConfiguration
    private class Config {
        @Bean
        fun sampleService() = spyk(SampleService())
    }


    @Test
    fun `Should get the message`() {
        mockMv.get("/test-mockk")
            .andExpect { MockMvcResultMatchers.status().isOk }
            .andExpect { MockMvcResultMatchers.jsonPath("$.message", Matchers.`is`("hello")) }
    }

    @Test
    fun `Should mock the response`() {
        every { sampleService.getMessage() } returns "mock message"
        mockMv.get("/test-mockk")
            .andExpect { MockMvcResultMatchers.status().isOk }
            .andExpect { MockMvcResultMatchers.jsonPath("$.message", Matchers.`is`("mock message")) }
    }
}