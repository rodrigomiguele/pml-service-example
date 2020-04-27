package rmiguele.transaction.validation.model

import java.util.Date

data class Transaction(val transactionType: TransactionType, val transactionCode: String, val value: Double, val date: Date, val senderCode: String, val receiverCode: String)

enum class TransactionType {
    MOBILE, CREDIT_CARD, ATM
}