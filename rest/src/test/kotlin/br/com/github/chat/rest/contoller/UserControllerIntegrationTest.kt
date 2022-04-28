package br.com.github.chat.rest.contoller

import br.com.github.chat.application.Boot
import br.com.github.chat.rest.controller.UserController
import br.com.github.chat.rest.generator.createUserCandidate
import br.com.github.chat.rest.generator.createUserCandidateMap
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.exhaustive
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [UserController::class])
@ContextConfiguration(classes = [Boot::class])
class UserControllerIntegrationTest : StringSpec() {

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
            .andExpect(status().isOk)
        }

        "should returns 400 when post request is invalid" {
            val requiredFields = listOf(
                "person",
                "person.name",
                "person.birthDate",
                "phoneNumber",
                "phoneNumber.areaCode",
                "phoneNumber.countryCode",
                "phoneNumber.number",
                "device",
                "device.manufacturer",
                "device.system",
                "device.system.version.",
                "device.system.systemOperation"
            )

            checkAll(requiredFields.exhaustive()) {requiredField ->
                val request = createUserCandidateMap(fieldIgnore = "person")

                mockMvc.perform(MockMvcRequestBuilders
                    .post(endpoint)
                    .contentType(contentTypePattern)
                    .content(objectToJsonByteArray(obj = request))
                )
                .andDo(print())
                .andExpect(status().isBadRequest)
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
