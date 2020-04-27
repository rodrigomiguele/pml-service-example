package rmiguele.transaction.validation.service.impl

import rmiguele.transaction.validation.model.TransactionType
import rmiguele.transaction.validation.service.RestrictedListValidationCommand
import rmiguele.transaction.validation.service.RestrictedListValidationService
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionCommand
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionService
import rmiguele.transaction.validation.service.ValidateTransactionCommand
import rmiguele.transaction.validation.service.ValidateTransactionService

class ValidateTransactionServiceImpl(private val validateCreditCardTransactionService: ValidateCreditCardTransactionService, private val restrictedListValidationService: RestrictedListValidationService) : ValidateTransactionService {

    override fun validate(command: ValidateTransactionCommand) {
        if (command.transactionType == TransactionType.CREDIT_CARD) {
            validateCreditCardTransactionService.validate(ValidateCreditCardTransactionCommand(command.transactionCode, command.receiverCode, command.value, command.date))
        }
        restrictedListValidationService.validate(RestrictedListValidationCommand(command.transactionCode, command.senderCode, command.receiverCode))
    }

}