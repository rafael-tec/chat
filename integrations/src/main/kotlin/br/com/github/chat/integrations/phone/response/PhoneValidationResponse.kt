package br.com.github.chat.integrations.phone.response

import com.fasterxml.jackson.annotation.JsonProperty

data class PhoneValidationResponse(
    @JsonProperty(required = true)
    val valid: Boolean,

    @JsonProperty(required = true)
    val number: String,

    @JsonProperty(value = "local_format", required = true)
    val localFormat: String,

    @JsonProperty(value = "international_format", required = true)
    val internationalFormat: String,

    @JsonProperty(value = "country_prefix", required = true)
    val countryPrefix: String,

    @JsonProperty(value = "country_code", required = true)
    val countryCode: String,

    @JsonProperty(value = "country_name", required = true)
    val countryName: String,

    @JsonProperty(required = true)
    val location: String,

    @JsonProperty(required = true)
    val carrier: String,

    @JsonProperty(value = "line_type", required = true)
    val lineType: String
)