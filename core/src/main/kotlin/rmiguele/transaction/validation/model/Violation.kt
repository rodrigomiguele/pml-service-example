package rmiguele.transaction.validation.model

import dev.morphia.annotations.Entity
import dev.morphia.annotations.Id

@Entity
data class Violation(
        @Id
        val code: String,
        val transactionCode: String,
        val description: String
)