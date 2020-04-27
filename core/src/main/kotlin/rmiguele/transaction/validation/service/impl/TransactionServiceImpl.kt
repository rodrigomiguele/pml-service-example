package rmiguele.transaction.validation.service.impl

import rmiguele.transaction.validation.model.Transaction
import rmiguele.transaction.validation.repository.TransactionRepository
import rmiguele.transaction.validation.service.CreateTransactionCommand
import rmiguele.transaction.validation.service.TransactionService

class TransactionServiceImpl(
        private val transactionRepository: TransactionRepository) : TransactionService {

    override fun createTransaction(command: CreateTransactionCommand): Transaction {
        return transactionRepository.save(Transaction(command.transactionCode, command.transactionType, command.value, command.date, command.senderCode, command.receiverCode))
    }

    override fun getTransactions(): List<Transaction> {
        return transactionRepository.findAll()
    }

}