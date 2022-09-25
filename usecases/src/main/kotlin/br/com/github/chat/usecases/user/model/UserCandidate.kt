package br.com.github.chat.usecases.user.model

import java.time.LocalDate

data class UserCandidate(
    val name: String,
    val birthDate: LocalDate,
    val email: String,
    val phoneNumber: PhoneNumberModel,
    val device: DeviceModel
)
