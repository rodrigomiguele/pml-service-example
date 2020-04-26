package rmiguele.transaction.validation.repository

import rmiguele.transaction.validation.model.Person

interface PersonRepository {

    fun getPersonById(personId: String): Person?

}