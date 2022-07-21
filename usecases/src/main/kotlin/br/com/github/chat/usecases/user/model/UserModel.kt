package br.com.github.chat.usecases.user.model

import br.com.github.chat.entities.UserEntity
import java.time.LocalDateTime

data class UserModel(
    val id: Int,
    val person: PersonModel,
    val createdAt: LocalDateTime
)

fun UserEntity.toModel() = UserModel(
    id = this.id!!,
    person = PersonModel(
        name = this.person.name,
        email = this.person.email,
        birthDate = this.person.birthDate
    ),
    createdAt = this.createdAt
)