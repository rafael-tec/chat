package br.com.github.chat.usecases.user.model

import java.time.LocalDateTime

data class UserModel(
    val id: Int,
    val person: PersonModel,
    val createdAt: LocalDateTime
)