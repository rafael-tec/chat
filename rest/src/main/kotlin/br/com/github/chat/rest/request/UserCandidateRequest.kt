package br.com.github.chat.rest.request

import java.time.LocalDateTime

data class UserCandidateRequest(
    val birthDate: LocalDateTime,
    val name: String,
    val phoneNumber: PhoneNumberRequest
)

data class PhoneNumberRequest(
    val areaCode: String,
    val countryCode: String,
    val number: String
)