package br.com.github.chat.rest.request

import javax.validation.constraints.NotNull

data class SystemRequest(
    @field:NotNull
    val version: String,

    @field:NotNull
    val systemOperation: String,
)