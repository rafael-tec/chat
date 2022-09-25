package br.com.github.chat.rest.controller

import br.com.github.chat.persistence.phone.PhoneValidatorService
import br.com.github.chat.usecases.user.model.PhoneNumberModel
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PhoneValidatorController(
    val validatorService: PhoneValidatorService
) {

    @GetMapping("/v1/phone")
    fun validate() {
        validatorService.validate(
            PhoneNumberModel(countryCode = "55", areaCode = "11", number = "948640911")
        )
    }

    @GetMapping("/validate")
    fun test(@RequestHeader httpHeaders: HttpHeaders, @RequestParam number: String) {
        println(httpHeaders)
        println(number)
    }
}