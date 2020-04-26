package rmiguele.transaction.validation.service.impl

import rmiguele.transaction.validation.model.TransactionType
import rmiguele.transaction.validation.service.*

class ValidateTransactionServiceImpl(val validateCreditCardTransactionService: ValidateCreditCardTransactionService, val restrictedListValidationService: RestrictedListValidationService) : ValidateTransactionService {

    override fun validate(command: ValidateTransactionCommand) {
        if (command.transactionType == TransactionType.CREDIT_CARD) {
            validateCreditCardTransactionService.validate(ValidateCreditCardTransactionCommand(command.transactionCode, command.receiverCode, command.value, command.date))
        }
        restrictedListValidationService.validate(RestrictedListValidationCommand(command.transactionCode, command.senderCode, command.receiverCode))
    }

}