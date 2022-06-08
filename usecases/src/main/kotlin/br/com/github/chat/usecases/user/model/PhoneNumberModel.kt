package br.com.github.chat.usecases.user.model

import javax.validation.constraints.NotNull

data class PhoneNumberModel(
    val areaCode: Int,
    val countryCode: Int,
    val number: Int
)