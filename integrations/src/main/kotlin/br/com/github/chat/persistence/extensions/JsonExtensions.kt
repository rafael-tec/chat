package br.com.github.chat.persistence.extensions

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun Any.convertToJson(): String = jacksonObjectMapper().writeValueAsString(this)

fun <T> convertTo(source: String?, type: Class<T>): T = jacksonObjectMapper().readValue(source, type)

fun String.convertToJsonNode(): JsonNode = jacksonObjectMapper().let { it.readTree(this) }