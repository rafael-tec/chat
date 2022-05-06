package br.com.github.chat.rest.exception

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.springframework.http.HttpStatus
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
            every { exception.allErrors } returns mockk()

            val defaultErrorResponse = handler.handleArgumentNotValidException(exception)

            defaultErrorResponse.status shouldBe HttpStatus.BAD_REQUEST.value()
            defaultErrorResponse.error shouldBe "INVALID_REQUEST_CONTRACT"
            defaultErrorResponse.timestamp shouldNotBe null
            defaultErrorResponse.message shouldNotBe null
        }
    }
}