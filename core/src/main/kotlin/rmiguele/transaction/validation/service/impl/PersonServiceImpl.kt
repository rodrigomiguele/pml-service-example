package rmiguele.transaction.validation.service.impl

import rmiguele.transaction.validation.model.Person
import rmiguele.transaction.validation.repository.PersonRepository
import rmiguele.transaction.validation.service.CreatePersonCommand
import rmiguele.transaction.validation.service.PersonService

class PersonServiceImpl(val personRepository: PersonRepository) : PersonService {

    override fun createPerson(command: CreatePersonCommand): Person {
        return personRepository.save(Person(command.personCode, command.personSituation))
    }

}