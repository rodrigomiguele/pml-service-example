package rmiguele.transaction.validation.service.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import rmiguele.transaction.validation.model.Transaction
import rmiguele.transaction.validation.model.TransactionType
import rmiguele.transaction.validation.repository.TransactionRepository
import rmiguele.transaction.validation.service.AddTransactionCommand
import java.util.Date

@ExtendWith(MockitoExtension::class)
class AddTransactionServiceImplTest {

    @Mock
    lateinit var transactionRepository: TransactionRepository

    @InjectMocks
    lateinit var addTransactionServiceImpl: AddTransactionServiceImpl

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Transaction>

    @Test
    fun saveTransaction() {
        val date = Date()
        addTransactionServiceImpl.addTransaction(AddTransactionCommand(TransactionType.CREDIT_CARD, "transaction1", 100.00, date, "sender1", "receiver1"))

        Mockito.verify(transactionRepository, Mockito.only()).save(TestUtils.capture(argumentCaptor))

        assertEquals(TransactionType.CREDIT_CARD, argumentCaptor.value.transactionType)
        assertEquals("transaction1", argumentCaptor.value.transactionCode)
        assertEquals(100.00, argumentCaptor.value.value)
        assertEquals(date, argumentCaptor.value.date)
        assertEquals("sender1", argumentCaptor.value.senderCode)
        assertEquals("receiver1", argumentCaptor.value.receiverCode)
    }
}