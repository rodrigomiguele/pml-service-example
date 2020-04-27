package rmiguele.transaction.validation.repository

import rmiguele.transaction.validation.model.Transaction
import rmiguele.transaction.validation.model.TransactionType

interface TransactionRepository: BaseRepository<String, Transaction> {

    fun getLastTransactionByTypeAndSender(transactionType: TransactionType, senderCode: String): Transaction?

}