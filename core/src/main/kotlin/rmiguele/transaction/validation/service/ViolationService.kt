package rmiguele.transaction.validation.service

import rmiguele.transaction.validation.model.Violation

data class CreateViolationCommand(
        val transactionCode: String,
        val description: String
)

data class GetViolationsByTransactionCodeQuery(
        val transactionCode: String
)

interface ViolationService {

    fun createViolation(command: CreateViolationCommand): Violation

    fun getViolations(query: GetViolationsByTransactionCodeQuery): List<Violation>

    fun getViolations(): List<Violation>

}
