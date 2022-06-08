package br.com.github.chat.usecases.user.model

import javax.validation.constraints.NotNull

data class SystemModel(
    val version: String,
    val systemOperation: String,
)