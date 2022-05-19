package br.com.github.chat.rest.contoller

import br.com.github.chat.rest.controller.UserController
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs

class UserControllerTest : StringSpec() {

    @InjectMockKs
    private lateinit var controller: UserController

    override suspend fun beforeTest(testCase: TestCase) {
        MockKAnnotations.init(this)
    }

    init {
        "should validate request successfully" { }
    }
}
