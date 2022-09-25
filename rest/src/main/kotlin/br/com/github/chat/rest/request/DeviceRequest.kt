package br.com.github.chat.rest.request

import javax.validation.Valid
import javax.validation.constraints.NotNull

data class DeviceRequest(
    @field:NotNull
    val manufacturer: String?,

    @field:NotNull
    @field:Valid
    val system: SystemRequest?,

    @field:NotNull
    val ip: String?,
)