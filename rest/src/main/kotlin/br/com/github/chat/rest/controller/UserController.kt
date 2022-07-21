package br.com.github.chat.rest.controller

import br.com.github.chat.rest.request.UserCandidateRequest
import br.com.github.chat.usecases.user.UserInteractor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/v1/user")
class UserController(
    val userInteractor: UserInteractor
) {

    @PostMapping
    fun save(
        @RequestBody
        @Valid
        userCandidateRequest: UserCandidateRequest
    ) = userInteractor.registration(userCandidate = userCandidateRequest.toModel())

    @GetMapping
    fun fetchAll() = userInteractor.fetchAll()
}