package br.com.github.chat.usecases.user.gateway

import br.com.github.chat.usecases.user.model.UserCandidateModel
import br.com.github.chat.usecases.user.model.UserModel

interface UserGateway {
    fun findAll(): List<UserModel>

    fun save(model: UserCandidateModel): UserModel
}