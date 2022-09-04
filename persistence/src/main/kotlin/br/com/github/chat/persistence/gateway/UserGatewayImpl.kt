package br.com.github.chat.persistence.gateway

import br.com.github.chat.persistence.entity.toEntity
import br.com.github.chat.persistence.repository.PersonRepository
import br.com.github.chat.persistence.repository.UserRepository
import br.com.github.chat.usecases.user.gateway.UserGateway
import br.com.github.chat.usecases.user.model.UserCandidateModel
import br.com.github.chat.usecases.user.model.UserModel
import org.springframework.stereotype.Component

@Component
class UserGatewayImpl(
    private val userRepository: UserRepository,
    private val personRepository: PersonRepository
): UserGateway {

    override fun findAll(): List<UserModel> =
        userRepository.findAll().map {
            it.toModel()
        }

    override fun save(model: UserCandidateModel): UserModel {
        val userEntity = model.toEntity()
        val personEntity = model.person.toEntity()

        personRepository.save(personEntity)

        return userRepository.save(userEntity).let {
            it.toModel()
        }
    }
}

