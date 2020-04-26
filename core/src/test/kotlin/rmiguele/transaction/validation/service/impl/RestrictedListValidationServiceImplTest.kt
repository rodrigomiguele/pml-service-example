package rmiguele.transaction.validation.service.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.BDDMockito.only
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import rmiguele.transaction.validation.model.Person
import rmiguele.transaction.validation.model.PersonSituation
import rmiguele.transaction.validation.repository.PersonRepository
import rmiguele.transaction.validation.service.AddViolationCommand
import rmiguele.transaction.validation.service.AddViolationService
import rmiguele.transaction.validation.service.RestrictedListValidationCommand
import rmiguele.transaction.validation.service.impl.TestUtils.Companion.any
import rmiguele.transaction.validation.service.impl.TestUtils.Companion.capture

@ExtendWith(MockitoExtension::class)
class RestrictedListValidationServiceImplTest {

    @Mock
    lateinit var personRepository: PersonRepository

    @Mock
    lateinit var addViolationService: AddViolationService

    @InjectMocks
    lateinit var restrictedListValidationServiceImpl: RestrictedListValidationServiceImpl

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<AddViolationCommand>

    @Test
    fun doNothingIfSenderAndReceiverNotFound() {
        given(personRepository.getPersonById("sender1")).willReturn(null)
        given(personRepository.getPersonById("receiver1")).willReturn(null)

        restrictedListValidationServiceImpl.validate(RestrictedListValidationCommand("transaction1", "sender1", "receiver1"))

        verify(addViolationService, never()).addViolation(any())
    }

    @Test
    fun doNothingIfSenderAndReceiverAreLegal() {
        given(personRepository.getPersonById("sender1")).willReturn(Person("sender1", PersonSituation.LEGAL))
        given(personRepository.getPersonById("receiver1")).willReturn(Person("receiver1", PersonSituation.LEGAL))

        restrictedListValidationServiceImpl.validate(RestrictedListValidationCommand("transaction1", "sender1", "receiver1"))

        verify(addViolationService, never()).addViolation(any())
    }

    @Test
    fun addViolationIfSenderIsIllegal() {
        given(personRepository.getPersonById("sender1")).willReturn(Person("sender1", PersonSituation.ILLEGAL))
        given(personRepository.getPersonById("receiver1")).willReturn(Person("receiver1", PersonSituation.LEGAL))

        restrictedListValidationServiceImpl.validate(RestrictedListValidationCommand("transaction1", "sender1", "receiver1"))

        verify(addViolationService, only()).addViolation(capture(argumentCaptor))
        assertEquals("transaction1", argumentCaptor.value.transactionCode)
        assertEquals("Remetente \"sender1\" em situação ilegal", argumentCaptor.value.description)
    }

    @Test
    fun addViolationIfReceiverIsIllegal() {
        given(personRepository.getPersonById("sender1")).willReturn(Person("sender1", PersonSituation.LEGAL))
        given(personRepository.getPersonById("receiver1")).willReturn(Person("receiver1", PersonSituation.ILLEGAL))

        restrictedListValidationServiceImpl.validate(RestrictedListValidationCommand("transaction1", "sender1", "receiver1"))

        verify(addViolationService, only()).addViolation(capture(argumentCaptor))
        assertEquals("transaction1", argumentCaptor.value.transactionCode)
        assertEquals("Receptor \"receiver1\" em situação ilegal", argumentCaptor.value.description)
    }
}