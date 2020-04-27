package rmiguele.transaction.validation.service

import rmiguele.transaction.validation.model.Person
import rmiguele.transaction.validation.model.PersonSituation

data class CreatePersonCommand(
        val personCode: String,
        val personSituation: PersonSituation
)

interface PersonService {

    fun createPerson(command: CreatePersonCommand): Person

}