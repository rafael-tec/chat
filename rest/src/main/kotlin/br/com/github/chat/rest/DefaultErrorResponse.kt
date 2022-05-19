package br.com.github.chat.rest

import java.time.Instant

data class DefaultErrorResponse(
    val timestamp: Instant = Instant.now(),
    val status: Int,
    val error: String,
    val message: Any
)