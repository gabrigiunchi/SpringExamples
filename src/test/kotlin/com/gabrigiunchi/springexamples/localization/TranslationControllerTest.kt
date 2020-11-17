package com.gabrigiunchi.springexamples.localization

import com.gabrigiunchi.springexamples.BaseWebTest
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

class TranslationControllerTest : BaseWebTest() {

    @Test
    fun `Should translate a message`() {
        mockMv.perform(
            MockMvcRequestBuilders.get("/translate/hello")
                .header("accept-language", "en")
        ).andExpect { MockMvcResultMatchers.status().isOk }
            .andExpect { MockMvcResultMatchers.jsonPath("$.message", Matchers.`is`("hello")) }

        mockMv.perform(
            MockMvcRequestBuilders.get("/translate/hello")
                .header("accept-language", "de")
        ).andExpect { MockMvcResultMatchers.status().isOk }
            .andExpect { MockMvcResultMatchers.jsonPath("$.message", Matchers.`is`("hallo")) }

        mockMv.perform(
            MockMvcRequestBuilders.get("/translate/hello")
                .header("accept-language", "it")
        ).andExpect { MockMvcResultMatchers.status().isOk }
            .andExpect { MockMvcResultMatchers.jsonPath("$.message", Matchers.`is`("ciao")) }

        mockMv.perform(
            MockMvcRequestBuilders.get("/translate/hello")
                .header("accept-language", "fr")
        ).andExpect { MockMvcResultMatchers.status().isOk }
            .andExpect { MockMvcResultMatchers.jsonPath("$.message", Matchers.`is`("salut")) }

        mockMv.perform(
            MockMvcRequestBuilders.get("/translate/hello")
                .header("accept-language", "sp")
        ).andExpect { MockMvcResultMatchers.status().isOk }
            .andExpect { MockMvcResultMatchers.jsonPath("$.message", Matchers.`is`("hello")) }

        Locale.setDefault(Locale.FRANCE)
        mockMv.get("/translate/hello")
            .andExpect { MockMvcResultMatchers.status().isOk }
            .andExpect { MockMvcResultMatchers.jsonPath("$.message", Matchers.`is`("salut")) }

        Locale.setDefault(Locale.ITALIAN)
        mockMv.get("/translate/hello")
            .andExpect { MockMvcResultMatchers.status().isOk }
            .andExpect { MockMvcResultMatchers.jsonPath("$.message", Matchers.`is`("ciao")) }
    }
}