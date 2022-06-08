package br.com.github.chat.usecases.user.model

import javax.validation.Valid
import javax.validation.constraints.NotNull

data class DeviceModel(
    val manufacturer: String,
    val system: SystemModel
)