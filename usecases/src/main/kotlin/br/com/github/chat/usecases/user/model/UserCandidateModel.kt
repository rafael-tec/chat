package br.com.github.chat.usecases.user.model

data class UserCandidateModel(
    val person: PersonModel,
    val phoneNumber: PhoneNumberModel,
    val device: DeviceModel
)
