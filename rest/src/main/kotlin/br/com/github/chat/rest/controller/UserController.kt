package br.com.github.chat.rest.controller

import br.com.github.chat.rest.request.UserCandidateRequest
import br.com.github.chat.rest.validator.RequestContractValidator
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/v1/user")
class UserController(
    private val requestContractValidator: RequestContractValidator
) {

    @PostMapping
    fun save(
        @RequestBody
        @Valid userCandidateRequest: UserCandidateRequest,
        bindingResult: BindingResult
    ) {
        requestContractValidator.validate(bindingResult)
    }
}