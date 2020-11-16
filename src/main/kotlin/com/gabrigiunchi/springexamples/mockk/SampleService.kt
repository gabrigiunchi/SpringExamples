package com.gabrigiunchi.springexamples.mockk

import org.springframework.stereotype.Service

@Service
class SampleService {

    fun getMessage(): String = "hello"
}