package br.com.github.chat.rest.validator

import io.kotest.core.spec.style.AnnotationSpec

class RequestContractValidatorTest : AnnotationSpec() {

    private val validator = RequestContractValidator()

    init {
        @Test
        fun `should validate the contract of a request`() { }
    }
}