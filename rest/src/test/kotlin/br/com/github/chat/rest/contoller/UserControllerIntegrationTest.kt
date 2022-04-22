package br.com.github.chat.rest.contoller

import br.com.github.chat.application.Boot
import br.com.github.chat.rest.controller.UserController
import br.com.github.chat.rest.validator.RequestContractValidator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@WebMvcTest(controllers = [UserController::class])
@ContextConfiguration(classes = [Boot::class])
class UserControllerIntegrationTest : StringSpec() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var requestContractValidator: RequestContractValidator

    override fun extensions() = listOf(SpringExtension)

    init {
        "should returns 200 when post request is valid" {
            val request = createUserCandidate()

            mockMvc.perform(post("/v1/user")
                .contentType("application/json")
                .content(objectToJsonByteArray(obj = request))
            )
        }
    }
}

private fun objectToJsonByteArray(obj: Any): ByteArray {
    val objectMapper = ObjectMapper()
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    return objectMapper
        .writeValueAsString(obj)
        .toByteArray(Charsets.UTF_8)
}
