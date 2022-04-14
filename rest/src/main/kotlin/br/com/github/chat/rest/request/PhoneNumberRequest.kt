package br.com.github.chat.rest.request

import javax.validation.constraints.NotNull

data class PhoneNumberRequest(
    @field:NotNull
    val areaCode: String,

    @field:NotNull
    val countryCode: String,

    @field:NotNull
    val number: String
)