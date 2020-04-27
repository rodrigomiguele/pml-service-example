package rmiguele.transaction.validation.service.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.given
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import rmiguele.transaction.validation.model.Transaction
import rmiguele.transaction.validation.model.TransactionType
import rmiguele.transaction.validation.repository.TransactionRepository
import rmiguele.transaction.validation.service.CreateTransactionCommand
import java.util.Date

@ExtendWith(MockitoExtension::class)
class TransactionServiceImplTest {

    @Mock
    lateinit var transactionRepository: TransactionRepository

    @InjectMocks
    lateinit var transactionServiceImpl: TransactionServiceImpl

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Transaction>

    @Test
    fun saveTransaction() {
        val date = Date()
        transactionServiceImpl.createTransaction(CreateTransactionCommand("transaction1", TransactionType.CREDIT_CARD, 100.00, date, "sender1", "receiver1"))

        Mockito.verify(transactionRepository, Mockito.only()).save(TestUtils.capture(argumentCaptor))

        assertEquals("transaction1", argumentCaptor.value.code)
        assertEquals(TransactionType.CREDIT_CARD, argumentCaptor.value.type)
        assertEquals(100.00, argumentCaptor.value.value)
        assertEquals(date, argumentCaptor.value.date)
        assertEquals("sender1", argumentCaptor.value.senderCode)
        assertEquals("receiver1", argumentCaptor.value.receiverCode)
    }

    @Test
    fun getAllTransactions() {
        val transactions = arrayListOf(
                Transaction(
                        "transaction1",
                        TransactionType.CREDIT_CARD,
                        100.00,
                        Date(),
                        "sender1",
                        "receiver1"
                )
        )
        given(transactionRepository.findAll()).willReturn(transactions)

        val found = transactionServiceImpl.getTransactions()
        assertEquals(transactions, found)
    }
}