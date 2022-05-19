package br.com.github.chat.rest.exception

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException

class ArgumentNotValidExceptionControllerTest : StringSpec() {

    @MockK
    private lateinit var exception: MethodArgumentNotValidException

    private val handler = ArgumentNotValidExceptionController()

    override suspend fun beforeSpec(spec: Spec) {
        MockKAnnotations.init(this)
    }

    init {
        "should return default error response successfully" {
            val fieldErrors = listOf(
                FieldError("objectName1", "field1", "must not be null"),
                FieldError("objectName2", "field2", "must not be null")
            )

            every { exception.fieldErrors } returns fieldErrors

            val defaultErrorResponse = handler.handleArgumentNotValidException(exception)

            defaultErrorResponse.timestamp shouldNotBe null
            defaultErrorResponse.status shouldBe HttpStatus.BAD_REQUEST.value()
            defaultErrorResponse.error shouldBe "INVALID_FIELD_VALUE"
            defaultErrorResponse.message shouldBe fieldErrors.associate { it.field to it.defaultMessage }
        }
    }
}