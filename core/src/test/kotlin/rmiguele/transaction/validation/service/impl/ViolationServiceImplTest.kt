package rmiguele.transaction.validation.service.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.given
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.only
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import rmiguele.transaction.validation.model.Violation
import rmiguele.transaction.validation.repository.ViolationRepository
import rmiguele.transaction.validation.service.CreateViolationCommand
import rmiguele.transaction.validation.service.GetViolationsByTransactionCodeQuery
import rmiguele.transaction.validation.service.impl.TestUtils.Companion.capture

@ExtendWith(MockitoExtension::class)
class ViolationServiceImplTest {

    @Mock
    lateinit var violationRepository: ViolationRepository

    @InjectMocks
    lateinit var violationServiceImpl: ViolationServiceImpl

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Violation>

    @Test
    fun saveViolation() {
        violationServiceImpl.createViolation(CreateViolationCommand("transaction1", "Description"))

        verify(violationRepository, only()).save(capture(argumentCaptor))

        assertEquals("transaction1", argumentCaptor.value.transactionCode)
        assertEquals("Description", argumentCaptor.value.description)
    }

    @Test
    fun getViolationsByTransactionCode() {
        val violations = arrayListOf(
                Violation("violation1", "transaction1", "Violação 1"),
                Violation("violation2", "transaction1", "Violação 2")
        )
        given(violationRepository.getViolationsByTransactionCode("transaction1")).willReturn(violations)

        val found = violationServiceImpl.getViolations(GetViolationsByTransactionCodeQuery("transaction1"))
        assertEquals(violations, found)
    }

    @Test
    fun getViolations() {
        val violations = arrayListOf(
                Violation("violation1", "transaction1", "Violação 1"),
                Violation("violation2", "transaction1", "Violação 2")
        )
        given(violationRepository.findAll()).willReturn(violations)

        val found = violationServiceImpl.getViolations()
        assertEquals(violations, found)
    }
}