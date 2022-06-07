package br.com.github.chat.rest.request

import br.com.github.chat.usecases.user.model.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

data class UserCandidateRequest(
    @field:NotNull
    @field:Valid
    val person: PersonRequest?,

    @field:NotNull
    @field:Valid
    val phoneNumber: PhoneNumberRequest?,

    @field:NotNull
    @field:Valid
    val device: DeviceRequest?,
) {
    fun toModel() = UserCandidateModel(
        person = PersonModel(
            name = this.person?.name,
            birthDate = this.person?.birthDate
        ),
        phoneNumber = PhoneNumberModel(
            areaCode = this.phoneNumber?.areaCode,
            countryCode = this.phoneNumber?.countryCode,
            number = this.phoneNumber?.number
        ),
        device = DeviceModel(
            manufacturer = this.device?.manufacturer,
            system = SystemModel(
                version = this.device?.system?.version,
                systemOperation = this.device?.system?.systemOperation
            )
        )
    )
}
