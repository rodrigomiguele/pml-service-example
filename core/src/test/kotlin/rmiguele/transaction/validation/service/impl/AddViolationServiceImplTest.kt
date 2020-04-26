package rmiguele.transaction.validation.service.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.only
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import rmiguele.transaction.validation.model.Violation
import rmiguele.transaction.validation.repository.ViolationRepository
import rmiguele.transaction.validation.service.AddViolationCommand
import rmiguele.transaction.validation.service.impl.TestUtils.Companion.capture

@ExtendWith(MockitoExtension::class)
class AddViolationServiceImplTest {

    @Mock
    lateinit var violationRepository: ViolationRepository

    @InjectMocks
    lateinit var addViolationServiceImpl: AddViolationServiceImpl

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Violation>

    @Test
    fun saveViolation() {
        addViolationServiceImpl.addViolation(AddViolationCommand("transaction1", "Description"))

        verify(violationRepository, only()).save(capture(argumentCaptor))

        assertEquals("transaction1", argumentCaptor.value.transactionCode)
        assertEquals("Description", argumentCaptor.value.description)
    }
}