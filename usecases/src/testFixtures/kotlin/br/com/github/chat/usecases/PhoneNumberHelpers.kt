package br.com.github.chat.usecases

import br.com.github.chat.usecases.user.model.PhoneNumberModel

fun phoneNumberModel() = PhoneNumberModel(
    areaCode = int(),
    countryCode = int(),
    number = int()
)