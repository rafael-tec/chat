package br.com.github.chat.rest.request

import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.constraints.NotNull

data class UserCandidateRequest(
    @NotNull
    val person: PersonRequest,

    @NotNull
    val phoneNumber: PhoneNumberRequest,

    @NotNull
    val device: DeviceRequest
)

data class PersonRequest(
    @NotNull
    val name: String,

    @NotNull
    val birthDate: LocalDate
)

data class DeviceRequest(
    @NotNull
    val manufacturer: String,

    @NotNull
    val system: SystemRequest
)

data class PhoneNumberRequest(
    @NotNull
    val areaCode: String,

    @NotNull
    val countryCode: String,

    @NotNull
    val number: String
)

data class SystemRequest(
    @NotNull
    val version: String,

    @NotNull
    val systemOperation: String,
)
