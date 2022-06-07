package br.com.github.chat.usecases.user.model

import java.time.LocalDate
import javax.validation.constraints.NotNull

data class PersonModel(
    @field:NotNull
    val name: String?,

    @field:NotNull
    val birthDate: LocalDate?
)