package br.com.github.chat.rest.contoller

import br.com.github.chat.rest.controller.UserController
import br.com.github.chat.rest.generator.createUserCandidate
import br.com.github.chat.rest.validator.RequestContractValidator
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.springframework.validation.BindingResult

class UserControllerTest : StringSpec() {

    @MockK
    private lateinit var requestContractValidator: RequestContractValidator

    @MockK
    private lateinit var bindingResult: BindingResult

    @InjectMockKs
    private lateinit var controller: UserController

    override suspend fun beforeTest(testCase: TestCase) {
        MockKAnnotations.init(this)
    }

    init {
        "should validate request successfully" {
            every { requestContractValidator.validate(any()) } just Runs

            controller.save(userCandidateRequest = createUserCandidate(), bindingResult)

            verify(exactly = 1) { requestContractValidator.validate(bindingResult) }
        }
    }
}
