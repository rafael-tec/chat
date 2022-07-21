package br.com.github.chat.usecases.user

import br.com.github.chat.usecases.user.model.UserCandidateModel
import br.com.github.chat.usecases.user.model.UserModel
import br.com.github.chat.usecases.user.model.toModel
import br.com.github.chat.usecases.user.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserInteractor(
    private val userRepository: UserRepository
) {

    fun fetchAll(): List<UserModel> =
        userRepository.findAll().map { it.toModel() }

    fun registration(userCandidate: UserCandidateModel): UserModel =
        userRepository.save(userCandidate.toEntity()).toModel()
}