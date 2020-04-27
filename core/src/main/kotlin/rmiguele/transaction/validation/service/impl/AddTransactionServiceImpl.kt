package rmiguele.transaction.validation.service.impl

import rmiguele.transaction.validation.model.Transaction
import rmiguele.transaction.validation.repository.TransactionRepository
import rmiguele.transaction.validation.service.AddTransactionCommand
import rmiguele.transaction.validation.service.AddTransactionService

class AddTransactionServiceImpl(private val transactionRepository: TransactionRepository) : AddTransactionService {

    override fun addTransaction(command: AddTransactionCommand) {
        transactionRepository.save(Transaction(command.transactionType, command.transactionCode, command.value, command.date, command.senderCode, command.receiverCode))
    }

}