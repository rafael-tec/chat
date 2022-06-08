package br.com.github.chat.rest.contoller

import br.com.github.chat.rest.configuration.IntegrationTestConfig
import br.com.github.chat.rest.controller.UserController
import br.com.github.chat.rest.generator.createUserCandidate
import br.com.github.chat.usecases.user.UserInteractor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.exhaustive
import org.hamcrest.Matchers.hasItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [UserController::class])
@ContextConfiguration(classes = [IntegrationTestConfig::class])
class UserControllerIntegrationTest : StringSpec() {

    @MockkBean
    private lateinit var userInteractor: UserInteractor

    @Autowired
    private lateinit var mockMvc: MockMvc
    override fun extensions() = listOf(SpringExtension)

    private val endpoint = "/v1/user"
    private val contentTypePattern = "application/json"

    init {
        "should returns 200 when post request is valid" {
            val request = createUserCandidate()

            mockMvc.perform(MockMvcRequestBuilders
                .post(endpoint)
                .contentType(contentTypePattern)
                .content(objectToJsonByteArray(obj = request))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
        }

        "should returns 400 when post request is invalid" {
            val requiredFields = listOf(
                "person",
                "person.name",
                "person.birthDate",
                "person.email",
                "phoneNumber",
                "phoneNumber.areaCode",
                "phoneNumber.countryCode",
                "phoneNumber.number",
                "device",
                "device.manufacturer",
                "device.system",
                "device.system.version",
                "device.system.systemOperation"
            )

            checkAll(requiredFields.exhaustive()) { requiredField ->
                val request = createUserCandidate(fieldIgnore = requiredField)
                val expectedMessage = mapOf(requiredField to "must not be null")

                mockMvc.perform(MockMvcRequestBuilders
                    .post(endpoint)
                    .contentType(contentTypePattern)
                    .content(objectToJsonByteArray(obj = request))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.error").value("INVALID_FIELD_VALUE"))
                .andExpect(jsonPath("$.*").value(hasItem(expectedMessage)))
            }
        }
    }
}

fun objectToJsonByteArray(obj: Any): ByteArray {
    val objectMapper = ObjectMapper()
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    return objectMapper
        .writeValueAsString(obj)
        .toByteArray(Charsets.UTF_8)
}
