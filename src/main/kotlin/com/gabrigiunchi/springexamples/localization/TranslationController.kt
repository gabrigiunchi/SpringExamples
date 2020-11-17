package com.gabrigiunchi.springexamples.localization

import org.springframework.context.MessageSource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/translate")
class TranslationController(private val messageSource: MessageSource) {

    @GetMapping("/{key}")
    fun translate(@PathVariable key: String, locale: Locale): ResponseEntity<Map<String, String>> {
        return ResponseEntity.ok(mapOf("message" to this.messageSource.getMessage(key, null, locale)))
    }
}