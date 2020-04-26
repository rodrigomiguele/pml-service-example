package rmiguele.transaction.validation.service.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.only
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import rmiguele.transaction.validation.model.Transaction
import rmiguele.transaction.validation.model.TransactionType
import rmiguele.transaction.validation.repository.TransactionRepository
import rmiguele.transaction.validation.service.AddViolationCommand
import rmiguele.transaction.validation.service.AddViolationService
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionCommand
import rmiguele.transaction.validation.service.impl.TestUtils.Companion.addMinutes
import rmiguele.transaction.validation.service.impl.TestUtils.Companion.any
import rmiguele.transaction.validation.service.impl.TestUtils.Companion.capture
import java.util.Date

@ExtendWith(MockitoExtension::class)
class ValidateCreditCardTransactionServiceImplTest {

    @Mock
    lateinit var transactionRepository: TransactionRepository

    @Mock
    lateinit var addViolationService: AddViolationService

    @InjectMocks
    lateinit var validateCreditCardTransactionServiceImpl: ValidateCreditCardTransactionServiceImpl

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<AddViolationCommand>

    @Test
    fun doNothingIfNoPreviousTransactionFromSender() {
        given(transactionRepository.getLastTransactionByTypeAndSender(TransactionType.CREDIT_CARD, "sender1")).willReturn(null)
        validateCreditCardTransactionServiceImpl.validate(ValidateCreditCardTransactionCommand("transaction1", "sender1", 100.00, Date()))
    }

    @Test
    fun doNothingIfPreviousTransactionHasNotTheSameValue() {
        val previousTransaction = Transaction(TransactionType.CREDIT_CARD, "transaction1", 100.00, Date(), "sender1", "receiver1")
        given(transactionRepository.getLastTransactionByTypeAndSender(TransactionType.CREDIT_CARD, "sender1")).willReturn(previousTransaction)

        validateCreditCardTransactionServiceImpl.validate(ValidateCreditCardTransactionCommand("transaction1", "sender1", 200.00, Date()))

        verify(addViolationService, never()).addViolation(any())
    }

    @Test
    fun doNothingIfPreviousTransactionHasTheSameValueButAfter5Minutes() {
        val date = Date()
        val previousDate = addMinutes(date, -6)
        val previousTransaction = Transaction(TransactionType.CREDIT_CARD, "transaction1", 100.00, previousDate, "sender1", "receiver1")
        given(transactionRepository.getLastTransactionByTypeAndSender(TransactionType.CREDIT_CARD, "sender1")).willReturn(previousTransaction)

        validateCreditCardTransactionServiceImpl.validate(ValidateCreditCardTransactionCommand("transaction1", "sender1", 100.00, date))

        verify(addViolationService, never()).addViolation(any())
    }

    @Test
    fun addViolationIfPreviousTransactionHasTheSameValueButBefore5Minutes() {
        val date = Date()
        val previousDate = addMinutes(date, -4)
        val previousTransaction = Transaction(TransactionType.CREDIT_CARD, "transaction1", 100.00, previousDate, "sender1", "receiver1")
        given(transactionRepository.getLastTransactionByTypeAndSender(TransactionType.CREDIT_CARD, "sender1")).willReturn(previousTransaction)

        validateCreditCardTransactionServiceImpl.validate(ValidateCreditCardTransactionCommand("transaction1", "sender1", 100.00, date))

        verify(addViolationService, only()).addViolation(capture(argumentCaptor))
        assertEquals("transaction1", argumentCaptor.value.transactionCode)
        assertEquals("Uma transação de mesmo valor já ocorreu nos últimos 5 minutos.", argumentCaptor.value.description)
    }

}