package rmiguele.transaction.validation.service

import rmiguele.transaction.validation.model.TransactionType
import java.util.Date

data class ValidateTransactionCommand(
        val transactionType: TransactionType,
        val transactionCode: String,
        val value: Double,
        val date: Date,
        val senderCode: String,
        val receiverCode: String
)

interface ValidateTransactionService {

    fun validate(command: ValidateTransactionCommand)

}