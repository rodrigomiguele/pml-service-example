package rmiguele.transaction.validation.service

data class AddViolationCommand(val transactionCode: String, val description: String)

interface AddViolationService {

    fun addViolation(command: AddViolationCommand)

}
