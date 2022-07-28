package br.com.github.chat.persistence.gateway

import br.com.github.chat.persistence.repository.UserRepository
import br.com.github.chat.usecases.user.gateway.UserGateway
import br.com.github.chat.usecases.user.model.UserCandidateModel
import br.com.github.chat.usecases.user.model.UserModel
import br.com.github.chat.usecases.user.model.toModel
import org.springframework.stereotype.Component

@Component
class UserGatewayImpl(
    private val userRepository: UserRepository
): UserGateway {

    override fun findAll(): List<UserModel> =
        userRepository.findAll().map { it.toModel() }

    override fun save(model: UserCandidateModel): UserModel =
        userRepository.save(model.toEntity()).toModel()
}