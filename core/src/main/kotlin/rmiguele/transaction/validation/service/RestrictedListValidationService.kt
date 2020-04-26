package rmiguele.transaction.validation.service

data class RestrictedListValidationCommand(val transactionCode: String, val senderCode: String, val receiverCode: String)

interface RestrictedListValidationService {

    fun validate(command: RestrictedListValidationCommand)

}