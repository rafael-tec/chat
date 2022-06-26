package br.com.github.chat.integrations.phone.response

import br.com.github.chat.integrations.extensions.convertTo
import br.com.github.chat.integrations.extensions.convertToJson
import br.com.github.chat.integrations.extensions.convertToJsonNode
import br.com.github.chat.integrations.helpers.phoneValidationResponse
import br.com.github.chat.usecases.string
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.exhaustive

class PhoneValidationResponseTest : StringSpec({

    "should parse json to model successfully" {
        val json = phoneValidationResponse().convertToJson()
        val jsonNode = json.convertToJsonNode()

        convertTo(source = json, type = PhoneValidationResponse::class.java).should {
            it.valid shouldBe jsonNode["valid"].asBoolean()
            it.number shouldBe jsonNode["number"].asText()
            it.localFormat shouldBe jsonNode["local_format"].asText()
            it.internationalFormat shouldBe jsonNode["international_format"].asText()
            it.countryPrefix shouldBe jsonNode["country_prefix"].asText()
            it.countryCode shouldBe jsonNode["country_code"].asText()
            it.countryName shouldBe jsonNode["country_name"].asText()
            it.location shouldBe jsonNode["location"].asText()
            it.carrier shouldBe jsonNode["carrier"].asText()
            it.lineType shouldBe jsonNode["line_type"].asText()
        }
    }

    "should throw exception in parse json to model when required field is missing" {
        val requiredProperties = listOf(
            "valid",
            "number",
            "local_format",
            "international_format",
            "country_prefix",
            "country_code",
            "country_name",
            "location",
            "carrier",
            "line_type",
        )

        checkAll(requiredProperties.exhaustive()) { requiredProperty ->
            val json = phoneValidationResponse(ignored = requiredProperty).convertToJson()

            shouldThrow<Exception> {
                convertTo(source = json, type = PhoneValidationResponse::class.java)
            }.should { exception ->
                exception.message shouldContain requiredProperty
            }
        }
    }
})

private fun phoneValidationResponse(ignored: String? = null) = mutableMapOf(
    "valid" to true,
    "number" to string(),
    "local_format" to string(),
    "international_format" to string(),
    "country_prefix" to string(),
    "country_code" to string(),
    "country_name" to string(),
    "location" to string(),
    "carrier" to string(),
    "line_type" to string()
).apply {
    this.remove(ignored)
}