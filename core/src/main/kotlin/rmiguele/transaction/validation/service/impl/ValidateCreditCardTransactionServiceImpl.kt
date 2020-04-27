package rmiguele.transaction.validation.service.impl

import rmiguele.transaction.validation.model.TransactionType
import rmiguele.transaction.validation.repository.TransactionRepository
import rmiguele.transaction.validation.service.AddViolationCommand
import rmiguele.transaction.validation.service.AddViolationService
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionCommand
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionService

class ValidateCreditCardTransactionServiceImpl(private val transactionRepository: TransactionRepository, private val addViolationService: AddViolationService) : ValidateCreditCardTransactionService {

    companion object {
        const val FIVE_MINUTES = 5 * 60 * 1000
    }

    override fun validate(command: ValidateCreditCardTransactionCommand) {
        transactionRepository.getLastTransactionByTypeAndSender(TransactionType.CREDIT_CARD, command.senderCode)?.also {
            if (command.value == it.value && command.date.time - it.date.time <= FIVE_MINUTES) {
                addViolationService.addViolation(AddViolationCommand(command.transactionCode, "Uma transa��o de mesmo valor j� ocorreu nos �ltimos 5 minutos."))
            }
        }
    }


}