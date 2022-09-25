package br.com.github.chat.usecases.user

import br.com.github.chat.usecases.user.gateway.UserRegistrationGateway
import br.com.github.chat.usecases.user.model.UserCandidate
import br.com.github.chat.usecases.user.model.UserModel
import org.springframework.stereotype.Component

@Component
class UserInteractor(
    private val userRepository: UserRegistrationGateway
) {

    fun fetchAll(): List<UserModel> =
        userRepository.findAll()

    fun registration(userCandidate: UserCandidate): UserModel =
        userRepository.save(userCandidate)
}