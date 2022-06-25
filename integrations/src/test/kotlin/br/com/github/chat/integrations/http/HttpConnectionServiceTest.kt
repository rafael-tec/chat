package br.com.github.chat.integrations.http

import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.should
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Conversions.string
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMapAdapter
import org.springframework.web.client.RestTemplate

class HttpConnectionServiceTest : StringSpec() {

    @MockK
    private lateinit var restTemplate: RestTemplate

    @InjectMockKs
    private lateinit var httpConnectionService: HttpConnectionService

    private var httpEntitySlot = slot<HttpEntity<*>>()

    private var responseEntity = responseEntity()

    override suspend fun beforeTest(testCase: TestCase) {
        MockKAnnotations.init(this)

        coEvery {
            restTemplate.exchange(any<String>(), any(), capture(httpEntitySlot), any<Class<Any>>())
        } returns responseEntity
    }

    init {
        "should send get request successfully" {
            val url = "http://localhots:8080"
            val queryParameters = mapOf("key" to "value")
            val customHeaders = mapOf("header" to "value")

            httpConnectionService.getRequest(url, queryParameters, customHeaders).should { response ->
                response.headers.shouldNotBeNull()
                response.statusCode.shouldNotBeNull()
                response.body.shouldNotBeNull()
            }

            val urlWithQueryParameters = queryParameters.let {
                "$url?${it.keys.first()}=${it.values.first()}"
            }

            coVerify(exactly = 1) {
                restTemplate.exchange(
                    urlWithQueryParameters,
                    HttpMethod.GET,
                    httpEntitySlot.captured,
                    Any::class.java
                )
            }
        }
    }

    private fun responseEntity() = ResponseEntity<Any>(
        string(),
        MultiValueMapAdapter(mapOf()),
        HttpStatus.ACCEPTED
    )
}
