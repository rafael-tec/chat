package br.com.github.chat.persistence.phone

import br.com.github.chat.persistence.extensions.convertTo
import br.com.github.chat.persistence.extensions.convertToJsonNode
import br.com.github.chat.persistence.http.HttpConnectionService
import br.com.github.chat.persistence.phone.response.PhoneValidationResponse
import br.com.github.chat.usecases.user.model.PhoneNumberModel
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class PhoneValidatorService(
    private val httpConnectionService: HttpConnectionService,
    environment: Environment
) {

    private val apiKey = environment.getRequiredProperty("API_KEY")

    private val numberVerificationUri = "https://api.apilayer.com/number_verification/validate"

    fun validate(phoneNumber: PhoneNumberModel): Boolean {
        return httpConnectionService.getRequest(
            url = numberVerificationUri,
            queryParameters = mapOf("number" to phoneNumber.fullPhoneNumber()),
            customHeaders = mapOf("apiKey" to apiKey)
        ).also {
            it.ensureStatusCodeIsSuccess()
            it.ensureResponseIsSuccess()
        }.let {
            parseJsonToModel(json = it.body).valid
        }
    }

    private fun ResponseEntity<String>.ensureStatusCodeIsSuccess(): ResponseEntity<*> {
        when(this.statusCode) {
            HttpStatus.OK -> return this
            HttpStatus.UNAUTHORIZED -> {
                throw Exception("Error in phone validation request. \nAPI Key is not valid")
            }
            else -> {
                throw Exception(
                    "Error in phone validation request. \nStatus Code: ${this.statusCode} and Body: ${this.body}"
                )
            }
        }
    }

    private fun ResponseEntity<String>.ensureResponseIsSuccess() {
        if(this.body?.existsErrorInBodyResponse() == true)
            throw RuntimeException("Exists error in body response")
    }

    private fun String.existsErrorInBodyResponse(): Boolean =
        this.convertToJsonNode()
            .let { it.has("error") && it.has("success") }

    private fun parseJsonToModel(json: String?): PhoneValidationResponse =
        try {
            convertTo(source = json, type = PhoneValidationResponse::class.java)
        } catch (exception: Exception) {
            throw RuntimeException("Cannot be convert json to model")
        }
}
