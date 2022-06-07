package br.com.github.chat.usecases.user.model

import javax.validation.constraints.NotNull

data class PhoneNumberModel(
    @field:NotNull
    val areaCode: Int?,

    @field:NotNull
    val countryCode: Int?,

    @field:NotNull
    val number: Int?
)