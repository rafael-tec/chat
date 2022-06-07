package br.com.github.chat.rest.contoller

import br.com.github.chat.rest.controller.UserController
import br.com.github.chat.rest.generator.createUserCandidate
import br.com.github.chat.usecases.user.UserInteractor
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK

class UserControllerTest : StringSpec() {

    @MockK
    private lateinit var userInteractor: UserInteractor

    @InjectMockKs
    private lateinit var controller: UserController

    override suspend fun beforeTest(testCase: TestCase) {
        MockKAnnotations.init(this)
    }

    init {
        "should validate request successfully" {
            val userCandidateRequest = createUserCandidate()

            every { userInteractor.registration(userCandidate = any()) } just Runs

            controller.save(userCandidateRequest)

            verify(exactly = 1) { userInteractor.registration(userCandidate = any()) }
        }
    }
}
