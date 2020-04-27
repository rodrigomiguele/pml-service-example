package rmiguele.transaction.validation.service.impl

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.only
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import rmiguele.transaction.validation.model.TransactionType
import rmiguele.transaction.validation.service.RestrictedListValidationService
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionService
import rmiguele.transaction.validation.service.ValidateTransactionCommand
import rmiguele.transaction.validation.service.impl.TestUtils.Companion.any
import java.util.Date

@ExtendWith(MockitoExtension::class)
class ValidateTransactionServiceImplTest {

    @Mock
    lateinit var validateCreditCardTransactionService: ValidateCreditCardTransactionService

    @Mock
    lateinit var restrictedListValidationService: RestrictedListValidationService

    @InjectMocks
    lateinit var validateTransactionServiceImpl: ValidateTransactionServiceImpl

    @Test
    fun validateCreditCard() {
        validateTransactionServiceImpl.validate(ValidateTransactionCommand("transaction1", TransactionType.CREDIT_CARD,  100.00, Date(), "sender1", "receiver1"))
        verify(validateCreditCardTransactionService, only()).validate(any())
        verify(restrictedListValidationService, only()).validate(any())
    }

    @Test
    fun doesNotValidateCreditCardForOtherTypes() {
        validateTransactionServiceImpl.validate(ValidateTransactionCommand("transaction1", TransactionType.ATM, 100.00, Date(), "sender1", "receiver1"))
        verify(validateCreditCardTransactionService, never()).validate(any())
        verify(restrictedListValidationService, only()).validate(any())
    }
}