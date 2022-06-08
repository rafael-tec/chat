package br.com.github.chat.rest.request

import java.time.LocalDate
import javax.validation.constraints.NotNull

data class PersonRequest(
    @field:NotNull
    val name: String?,

    @field:NotNull
    val birthDate: LocalDate?,

    @field:NotNull
    val email: String?
)