package br.com.github.chat.rest.exception

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MethodArgumentNotValidExceptionHandler {

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    @ResponseStatus(value = BAD_REQUEST)
    fun handleArgumentNotValidException(exception: MethodArgumentNotValidException): String {
        return "error"
    }
}