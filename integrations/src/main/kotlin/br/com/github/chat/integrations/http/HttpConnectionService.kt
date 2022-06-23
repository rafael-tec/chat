package br.com.github.chat.integrations.http

import org.springframework.core.annotation.MergedAnnotationCollectors
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@Service
class HttpConnectionService(
    private val restTemplate: RestTemplate
) {

    fun getRequest(url: String) = restTemplate.getForObject<String>(url = url)

    fun getRequest(url: String, pathParameters: String?) {
        restTemplate.getForObject<String>(url = url)
    }

    fun getRequest(
        url: String,
        queryParameters: Map<String, Any>,
        customHeaders: Map<String, String>
    ): ResponseEntity<String> {
        val httpHeaders = HttpHeaders()
            .apply { customHeaders.map { this.set(it.key, it.value) } }

        val uriWithQueryParams = UriComponentsBuilder
            .fromUriString(url)
            .apply { queryParameters.map { this.queryParam(it.key, it.value) } }
            .toUriString()

        return restTemplate.exchange(
            uriWithQueryParams,
            HttpMethod.GET,
            HttpEntity(null, httpHeaders),
            String::class.java,
        )
    }
}

data class HttpResponse(
    val statusCode: Int,
    val body: String
)