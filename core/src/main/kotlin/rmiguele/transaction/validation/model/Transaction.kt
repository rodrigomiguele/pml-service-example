package rmiguele.transaction.validation.model

import dev.morphia.annotations.Entity
import dev.morphia.annotations.Id
import java.util.Date

@Entity
data class Transaction(
        @Id
        val code: String,
        val type: TransactionType,
        val value: Double,
        val date: Date,
        val senderCode: String,
        val receiverCode: String
)

enum class TransactionType {
    MOBILE, CREDIT_CARD, ATM
}