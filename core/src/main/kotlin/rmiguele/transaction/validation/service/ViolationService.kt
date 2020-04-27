package rmiguele.transaction.validation.service

import rmiguele.transaction.validation.model.Violation

data class AddViolationCommand(
        val transactionCode: String,
        val description: String
)

data class GetViolationQuery(
        val transactionCode: String
)

interface ViolationService {

    fun addViolation(command: AddViolationCommand)

    fun getViolations(command: GetViolationQuery): List<Violation>
}
