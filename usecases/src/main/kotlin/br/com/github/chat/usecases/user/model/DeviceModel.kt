package br.com.github.chat.usecases.user.model

data class DeviceModel(
    val manufacturer: String,
    val system: SystemModel,
    val ip: String
)