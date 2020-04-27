package rmiguele.transaction.validation.service.impl

import rmiguele.transaction.validation.model.PersonSituation
import rmiguele.transaction.validation.repository.PersonRepository
import rmiguele.transaction.validation.service.CreateViolationCommand
import rmiguele.transaction.validation.service.RestrictedListValidationCommand
import rmiguele.transaction.validation.service.RestrictedListValidationService
import rmiguele.transaction.validation.service.ViolationService

class RestrictedListValidationServiceImpl(
        private val personRepository: PersonRepository,
        private val violationService: ViolationService) : RestrictedListValidationService {

    override fun validate(command: RestrictedListValidationCommand) {
        personRepository.findOne(command.senderCode)?.also {
            if (it.situation == PersonSituation.ILLEGAL) {
                violationService.createViolation(CreateViolationCommand(command.transactionCode, "Remetente \"${it.code}\" em situação ilegal"))
            }
        }

        personRepository.findOne(command.receiverCode)?.also {
            if (it.situation == PersonSituation.ILLEGAL) {
                violationService.createViolation(CreateViolationCommand(command.transactionCode, "Receptor \"${it.code}\" em situação ilegal"))
            }
        }
    }

}