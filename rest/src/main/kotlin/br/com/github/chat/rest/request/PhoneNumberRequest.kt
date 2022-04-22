package br.com.github.chat.rest.request

import javax.validation.constraints.NotNull

data class PhoneNumberRequest(
    @field:NotNull
    val areaCode: Int?,

    @field:NotNull
    val countryCode: Int?,

    @field:NotNull
    val number: Int?
)