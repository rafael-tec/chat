package br.com.github.chat.usecases.user.model

import javax.validation.constraints.NotNull

data class SystemModel(
    @field:NotNull
    val version: String?,

    @field:NotNull
    val systemOperation: String?,
)