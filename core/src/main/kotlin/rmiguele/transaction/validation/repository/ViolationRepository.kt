package rmiguele.transaction.validation.repository

import rmiguele.transaction.validation.model.Violation

interface ViolationRepository {

    fun save(violation: Violation)

}