package rmiguele.transaction.validation.service.impl

import rmiguele.transaction.validation.model.Violation
import rmiguele.transaction.validation.repository.ViolationRepository
import rmiguele.transaction.validation.service.AddViolationCommand
import rmiguele.transaction.validation.service.GetViolationQuery
import rmiguele.transaction.validation.service.ViolationService
import java.util.UUID

class ViolationServiceImpl(private val violationRepository: ViolationRepository) : ViolationService {

    override fun addViolation(command: AddViolationCommand) {
        violationRepository.save(Violation(UUID.randomUUID().toString(), command.transactionCode, command.description))
    }

    override fun getViolations(command: GetViolationQuery): List<Violation> {
        return violationRepository.getViolationsByTransactionCode(command.transactionCode)
    }

}