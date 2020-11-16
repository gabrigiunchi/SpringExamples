package com.gabrigiunchi.springexamples.mockk

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class Response(val message: String)

@RestController
@RequestMapping("/test-mockk")
class SampleController(private val sampleService: SampleService) {

    @GetMapping
    fun sampleEndpoint(): ResponseEntity<Response> {
        return ResponseEntity.ok(Response(this.sampleService.getMessage()))
    }
}