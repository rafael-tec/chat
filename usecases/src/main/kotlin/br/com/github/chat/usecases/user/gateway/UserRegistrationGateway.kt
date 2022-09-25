package br.com.github.chat.usecases.user.gateway

import br.com.github.chat.usecases.user.model.UserCandidate
import br.com.github.chat.usecases.user.model.UserModel

interface UserRegistrationGateway {
    fun findAll(): List<UserModel>

    fun save(model: UserCandidate): UserModel
}