package br.com.github.chat.usecases.user

import br.com.github.chat.usecases.user.gateway.UserGateway
import br.com.github.chat.usecases.user.model.UserCandidateModel
import br.com.github.chat.usecases.user.model.UserModel
import org.springframework.stereotype.Component

@Component
class UserInteractor(
    private val userRepository: UserGateway
) {

    fun fetchAll(): List<UserModel> =
        userRepository.findAll()

    fun registration(userCandidate: UserCandidateModel): UserModel =
        userRepository.save(userCandidate)
}