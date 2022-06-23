package br.com.github.chat.integrations.phone

import br.com.github.chat.integrations.http.HttpConnectionService
import br.com.github.chat.usecases.user.model.PhoneNumberModel
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class PhoneValidatorService(
    private val httpConnectionService: HttpConnectionService
) {

    @Value("#{environment.API_KEY}")
    private lateinit var apiKey: String

    private val publicApisUrl = "http://localhost:8080/validate"

    fun validate(phoneNumber: PhoneNumberModel): String {
        val response = httpConnectionService.getRequest(
            url = publicApisUrl,
            queryParameters = mapOf("number" to phoneNumber.fullPhoneNumber()),
            customHeaders = mapOf("apiKey" to apiKey)
        )

        return response.body?.toString() ?: ""
    }

    fun PhoneNumberModel.fullPhoneNumber() = "${this.countryCode}${this.areaCode}${this.number}"
}
