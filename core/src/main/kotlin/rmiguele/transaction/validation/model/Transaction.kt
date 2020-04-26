package rmiguele.transaction.validation.model

import java.math.BigDecimal
import java.util.*

data class Transaction(val transactionType: TransactionType, val transactionCode: String, val value: Double, val date: Date, val senderCode: String, val receiverCode: String)

enum class TransactionType {
    MOBILE, CREDIT_CARD, ATM
}