package br.com.github.chat.rest.controller

import br.com.github.chat.rest.request.UserCandidateRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/v1/user")
class UserController {

    @GetMapping
    fun save(
        @RequestBody
        @Valid
        userCandidateRequest: UserCandidateRequest
    ) {

    }
}