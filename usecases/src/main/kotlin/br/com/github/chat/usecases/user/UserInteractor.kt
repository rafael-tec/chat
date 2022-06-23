package br.com.github.chat.usecases.user

import br.com.github.chat.usecases.user.model.UserCandidateModel
import org.springframework.stereotype.Component

@Component
class UserInteractor {

    private val database = mutableSetOf<UserCandidateModel>()

    fun registration(userCandidate: UserCandidateModel) {
        database.add(userCandidate)
    }
}