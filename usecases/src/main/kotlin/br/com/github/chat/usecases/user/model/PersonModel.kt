package br.com.github.chat.usecases.user.model

import br.com.github.chat.entities.PersonEntity
import java.time.LocalDate

data class PersonModel(
    val name: String,
    val birthDate: LocalDate,
    val email: String
)