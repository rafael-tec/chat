package br.com.github.chat.usecases.user.model

import java.time.LocalDate

data class PersonModel(
    val name: String,
    val birthDate: LocalDate,
    val email: String
)