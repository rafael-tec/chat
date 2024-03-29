package br.com.github.chat.usecases.user.model

import java.time.LocalDate
import java.time.LocalDateTime

data class UserModel(
    val id: Int,
    val name: String,
    val email: String,
    val birthDate: LocalDate,
    val createdAt: LocalDateTime
)