package rmiguele.transaction.validation.service.impl

import rmiguele.transaction.validation.model.Violation
import rmiguele.transaction.validation.repository.ViolationRepository
import rmiguele.transaction.validation.service.AddViolationCommand
import rmiguele.transaction.validation.service.AddViolationService

class AddViolationServiceImpl(val violationRepository: ViolationRepository) : AddViolationService {

    override fun addViolation(command: AddViolationCommand) {
        violationRepository.save(Violation(command.transactionCode, command.description))
    }

}