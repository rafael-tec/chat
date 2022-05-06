package br.com.github.chat.rest.exception

import br.com.github.chat.rest.configuration.IntegrationTestConfig
import br.com.github.chat.rest.contoller.objectToJsonByteArray
import br.com.github.chat.rest.controller.UserController
import br.com.github.chat.rest.generator.createUserCandidate
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [ArgumentNotValidExceptionController::class, UserController::class])
@ContextConfiguration(classes = [IntegrationTestConfig::class, UserController::class])
class ArgumentNotValidExceptionHandlerIntegrationTest : StringSpec() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    override fun extensions() = listOf(SpringExtension)

    init {
        "should handler request when request is invalid" {
            val request = createUserCandidate(fieldIgnore = "person")

            mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/user")
                .contentType("application/json")
                .content(objectToJsonByteArray(obj = request))
            )
                .andExpect(status().isBadRequest)
                .andExpectAll(
                    jsonPath("$.status").value("400"),
                    jsonPath("$.error").value("INVALID_REQUEST_CONTRACT"),
                    jsonPath("$.message").value("bla"),
                    jsonPath("$.timestamp").exists()
                )
        }
    }
}