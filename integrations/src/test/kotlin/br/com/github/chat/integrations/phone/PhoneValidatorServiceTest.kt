package br.com.github.chat.integrations.phone

import br.com.github.chat.integrations.extensions.convertToJson
import br.com.github.chat.integrations.helpers.phoneValidationResponse
import br.com.github.chat.integrations.http.HttpConnectionService
import br.com.github.chat.usecases.phoneNumberModel
import br.com.github.chat.usecases.string
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMapAdapter

class PhoneValidatorServiceTest : StringSpec() {

    @MockK
    private var environment: Environment = mockk {
        coEvery { getRequiredProperty("API_KEY") } answers { firstArg() }
    }

    @MockK
    private lateinit var httpConnectionService: HttpConnectionService

    @InjectMockKs
    private lateinit var phoneValidatorService: PhoneValidatorService

    private val urlSlot = slot<String>()
    private val apiKeySlot = slot<Map<String, String>>()

    override suspend fun beforeTest(testCase: TestCase) {
        MockKAnnotations.init(this)

        coEvery {
            httpConnectionService.getRequest(capture(urlSlot), any(), capture(apiKeySlot))
        } returns responseEntity()
    }

    init {
        "should validate phone number successfully" {
            val phoneNumber = phoneNumberModel()

            phoneValidatorService.validate(phoneNumber).should { isValid ->
                isValid shouldBe true
            }

            coVerify(exactly = 1) {
                httpConnectionService.getRequest(
                    url = urlSlot.captured,
                    queryParameters = mapOf("number" to phoneNumber.fullPhoneNumber()),
                    customHeaders = apiKeySlot.captured
                )
            }
        }

        "should throw exception when response status code is 401" {
            val phoneNumber = phoneNumberModel()
            val responseEntity = responseEntity(httpStatus = HttpStatus.UNAUTHORIZED, body = string())

            coEvery { httpConnectionService.getRequest(any(), any(), any()) } returns responseEntity

            shouldThrow<Exception> {
                phoneValidatorService.validate(phoneNumber)
            }.should { exception ->
                exception.message shouldBe "Error in phone validation request. \nAPI Key is not valid"
            }
        }

        "should throw exception when response status code it's not 200 and 401" {
            val phoneNumber = phoneNumberModel()
            val responseEntity = responseEntity(httpStatus = HttpStatus.INTERNAL_SERVER_ERROR, body = string())

            coEvery { httpConnectionService.getRequest(any(), any(), any()) } returns responseEntity

            shouldThrow<Exception> {
                phoneValidatorService.validate(phoneNumber)
            }.should { exception ->
                exception.message shouldBe "Error in phone validation request. " +
                        "\nStatus Code: ${responseEntity.statusCode} and Body: ${responseEntity.body}"
            }
        }

        "should throw exception when status code is 200 but body has an error body" {
            val phoneNumber = phoneNumberModel()
            val responseEntity = responseEntity(body = bodyWithError())

            coEvery { httpConnectionService.getRequest(any(), any(), any()) } returns responseEntity

            shouldThrow<RuntimeException> {
                phoneValidatorService.validate(phoneNumber)
            }.should { exception ->
                exception.message shouldBe "Exists error in body response"
            }
        }

        "should throw exception when convert response body to response model fail" {
            val phoneNumber = phoneNumberModel()
            val responseEntity = responseEntity(body = mapOf(string() to string()).convertToJson())

            coEvery { httpConnectionService.getRequest(any(), any(), any()) } returns responseEntity

            shouldThrow<RuntimeException> {
                phoneValidatorService.validate(phoneNumber)
            }.should { exception ->
                exception.message shouldBe "Cannot be convert json to model"
            }
        }
    }
}

private fun bodyWithError() = mapOf("success" to "fail", "error" to string()).convertToJson()

private fun responseEntity(
    apiKey: String = string(),
    body: String = phoneValidationResponse().convertToJson(),
    httpStatus: HttpStatus = HttpStatus.OK
) = ResponseEntity(
    body,
    MultiValueMapAdapter(mutableMapOf("headers" to mutableListOf<String>())).apply { this.add("API_KEY", apiKey) },
    httpStatus
)
