package rmiguele.transaction.validation.service

import java.util.Date

data class ValidateCreditCardTransactionCommand(
        val transactionCode: String,
        val senderCode: String,
        val value: Double,
        val date: Date
)

interface ValidateCreditCardTransactionService {

    fun validate(command: ValidateCreditCardTransactionCommand)

}