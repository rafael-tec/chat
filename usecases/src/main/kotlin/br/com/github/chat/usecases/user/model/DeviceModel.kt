package br.com.github.chat.usecases.user.model

import javax.validation.Valid
import javax.validation.constraints.NotNull

data class DeviceModel(
    @field:NotNull
    val manufacturer: String?,

    @field:NotNull
    @field:Valid
    val system: SystemModel?
)