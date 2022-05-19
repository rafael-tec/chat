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

fun createUserCandidate(
    fieldIgnore: String? = null
) : MutableMap<String, Any> {
    val lastKey = fieldIgnore?.substringAfterLast(".")

    val person = mutableMapOf(
        "name" to string(),
        "birthDate" to LocalDate.now()
    ).apply { this.remove(lastKey) }

    val phoneNumber = mutableMapOf(
        "areaCode" to int(),
        "countryCode" to int(),
        "number" to int()
    ).apply { this.remove(lastKey) }

    val system = mutableMapOf(
        "version" to string(),
        "systemOperation" to string()
    ).apply { this.remove(lastKey) }

    val device = mutableMapOf(
        "manufacturer" to string(),
        "system" to system
    ).apply { this.remove(lastKey) }

    return mutableMapOf<String, Any>(
        "person" to person,
        "phoneNumber" to phoneNumber,
        "device" to device
    ).apply { this.remove(lastKey) }
}
