package rmiguele.transaction.validation.service.impl

import rmiguele.transaction.validation.model.PersonSituation
import rmiguele.transaction.validation.repository.PersonRepository
import rmiguele.transaction.validation.service.AddViolationCommand
import rmiguele.transaction.validation.service.AddViolationService
import rmiguele.transaction.validation.service.RestrictedListValidationCommand
import rmiguele.transaction.validation.service.RestrictedListValidationService

class RestrictedListValidationServiceImpl(private val personRepository: PersonRepository, private val addViolationService: AddViolationService) : RestrictedListValidationService {

    override fun validate(command: RestrictedListValidationCommand) {
        personRepository.getPersonById(command.senderCode)?.also {
            if (it.personSituation == PersonSituation.ILLEGAL) {
                addViolationService.addViolation(AddViolationCommand(command.transactionCode, "Remetente \"${it.personCode}\" em situação ilegal"))
            }
        }

        personRepository.getPersonById(command.receiverCode)?.also {
            if (it.personSituation == PersonSituation.ILLEGAL) {
                addViolationService.addViolation(AddViolationCommand(command.transactionCode, "Receptor \"${it.personCode}\" em situação ilegal"))
            }
        }
    }

}