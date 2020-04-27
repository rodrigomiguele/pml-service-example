package rmiguele.transaction.validation.service.impl

import rmiguele.transaction.validation.model.Violation
import rmiguele.transaction.validation.repository.ViolationRepository
import rmiguele.transaction.validation.service.CreateViolationCommand
import rmiguele.transaction.validation.service.GetViolationsByTransactionCodeQuery
import rmiguele.transaction.validation.service.ViolationService
import java.util.UUID

class ViolationServiceImpl(private val violationRepository: ViolationRepository) : ViolationService {

    override fun createViolation(command: CreateViolationCommand): Violation {
        return violationRepository.save(Violation(UUID.randomUUID().toString(), command.transactionCode, command.description))
    }

    override fun getViolations(query: GetViolationsByTransactionCodeQuery): List<Violation> {
        return violationRepository.getViolationsByTransactionCode(query.transactionCode)
    }

    override fun getViolations(): List<Violation> {
        return violationRepository.findAll()
    }

}