package br.com.github.chat.rest.request

import javax.validation.Valid
import javax.validation.constraints.NotNull

data class UserCandidateRequest(
    @field:NotNull
    @field:Valid
    val person: PersonRequest?,

    @field:NotNull
    @field:Valid
    val phoneNumber: PhoneNumberRequest?,

    @field:NotNull
    @field:Valid
    val device: DeviceRequest?,
)
