package br.com.github.chat.rest.validator

import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException

@Component
class RequestContractValidator {

    fun validate(bindingResult: BindingResult) {
        if(bindingResult.hasErrors()) {
            throw MethodArgumentNotValidException(
                MethodParameter(javaClass.getDeclaredMethod("validate", BindingResult::class.java), 0),
                bindingResult
            )
        }
    }
}
