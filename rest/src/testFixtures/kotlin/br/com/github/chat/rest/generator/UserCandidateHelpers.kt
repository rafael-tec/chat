package br.com.github.chat.rest.generator

import br.com.github.chat.rest.request.*
import br.com.github.chat.usecases.int
import br.com.github.chat.usecases.string
import java.time.LocalDate

fun createUserCandidate() = UserCandidateRequest(
    person = PersonRequest(
        name = string(),
        birthDate = LocalDate.now()
    ),
    phoneNumber = PhoneNumberRequest(
        areaCode = int(),
        countryCode = int(),
        number = int()
    ),
    device = DeviceRequest(
        manufacturer = string(),
        system = SystemRequest(
            version = string(),
            systemOperation = string()
        )
    )
)
