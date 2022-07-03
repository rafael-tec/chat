package br.com.github.chat.persistence.helpers

import br.com.github.chat.persistence.phone.response.PhoneValidationResponse
import br.com.github.chat.usecases.int
import br.com.github.chat.usecases.string

fun phoneValidationResponse(
    valid: Boolean = true
) = PhoneValidationResponse(
    valid = valid,
    number = int().toString(),
    localFormat = string(),
    internationalFormat = string(),
    countryPrefix = string(),
    countryCode = string(),
    countryName = string(),
    location = string(),
    carrier = string(),
    lineType = string()
)
