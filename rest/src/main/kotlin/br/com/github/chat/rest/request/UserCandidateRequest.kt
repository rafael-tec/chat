package br.com.github.chat.rest.request

import java.time.LocalDateTime
import javax.validation.constraints.NotNull

data class UserCandidateRequest(
    @NotNull
    val birthDate: LocalDateTime,

    @NotNull
    val name: String,

    @NotNull
    val phoneNumber: PhoneNumberRequest
)

data class PhoneNumberRequest(
    @NotNull
    val areaCode: String,

    @NotNull
    val countryCode: String,

    @NotNull
    val number: String
)