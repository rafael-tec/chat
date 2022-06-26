package br.com.github.chat.integrations.http

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class HttpConnectionService(
    private val restTemplate: RestTemplate
) {

    fun getRequest(
        url: String,
        queryParameters: Map<String, Any>,
        customHeaders: Map<String, String>
    ): ResponseEntity<String> {
        return restTemplate.exchange(
            url.withQueryParameters(queryParameters),
            HttpMethod.GET,
            HttpEntity(null, withCustomHeaders(customHeaders)),
            String::class.java,
        )
    }

    private fun withCustomHeaders(parameters: Map<String, String>) = HttpHeaders()
        .apply { parameters.map { this.set(it.key, it.value) } }

    private fun String.withQueryParameters(parameters: Map<String, Any>) =
        UriComponentsBuilder
            .fromUriString(this)
            .apply { parameters.map { this.queryParam(it.key, it.value) } }
            .toUriString()
}
