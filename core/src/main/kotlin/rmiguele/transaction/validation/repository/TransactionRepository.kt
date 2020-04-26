package rmiguele.transaction.validation.repository

import rmiguele.transaction.validation.model.Transaction
import rmiguele.transaction.validation.model.TransactionType

interface TransactionRepository {

    fun getLastTransactionByTypeAndSender(transactionType: TransactionType, senderCode: String): Transaction?

    fun save(transaction: Transaction)

}