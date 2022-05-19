package br.com.github.chat.rest.exception

import br.com.github.chat.rest.DefaultErrorResponse
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ArgumentNotValidExceptionController {

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleArgumentNotValidException(exception: MethodArgumentNotValidException): DefaultErrorResponse {

        return DefaultErrorResponse(
            status = BAD_REQUEST.value(),
            error = "INVALID_FIELD_VALUE",
            message = exception.fieldErrors.associate { it.field to it.defaultMessage }
        )
    }
}