package rmiguele.transaction.validation.service.impl

import rmiguele.transaction.validation.model.TransactionType
import rmiguele.transaction.validation.repository.TransactionRepository
import rmiguele.transaction.validation.service.CreateViolationCommand
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionCommand
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionService
import rmiguele.transaction.validation.service.ViolationService

class ValidateCreditCardTransactionServiceImpl(
        private val transactionRepository: TransactionRepository,
        private val violationService: ViolationService) : ValidateCreditCardTransactionService {

    companion object {
        const val FIVE_MINUTES = 5 * 60 * 1000
    }

    override fun validate(command: ValidateCreditCardTransactionCommand) {
        transactionRepository.getLastTransactionByTypeAndSender(TransactionType.CREDIT_CARD, command.senderCode)?.also {
            if (command.value == it.value && command.date.time - it.date.time <= FIVE_MINUTES) {
                violationService.createViolation(CreateViolationCommand(command.transactionCode,
                        "Uma transação de mesmo valor já ocorreu nos últimos 5 minutos."))
            }
        }
    }


}