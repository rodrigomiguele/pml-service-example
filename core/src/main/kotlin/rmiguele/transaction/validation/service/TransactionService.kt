package rmiguele.transaction.validation.service

import rmiguele.transaction.validation.model.Transaction
import rmiguele.transaction.validation.model.TransactionType
import java.util.Date

data class CreateTransactionCommand(
        val transactionCode: String,
        val transactionType: TransactionType,
        val value: Double,
        val date: Date,
        val senderCode: String,
        val receiverCode: String
)

interface TransactionService {

    fun createTransaction(command: CreateTransactionCommand): Transaction

    fun getTransactions(): List<Transaction>

}