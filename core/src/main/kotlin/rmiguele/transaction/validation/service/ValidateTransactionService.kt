package rmiguele.transaction.validation.service

import rmiguele.transaction.validation.model.TransactionType
import java.math.BigDecimal
import java.util.*

data class ValidateTransactionCommand(val transactionType: TransactionType, val transactionCode: String, val value: Double, val date: Date, val senderCode: String, val receiverCode: String)

interface ValidateTransactionService {

    fun validate(command: ValidateTransactionCommand)

}