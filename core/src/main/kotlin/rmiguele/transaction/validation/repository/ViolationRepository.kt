package rmiguele.transaction.validation.repository

import rmiguele.transaction.validation.model.Violation

interface ViolationRepository : BaseRepository<String, Violation> {

    fun getViolationsByTransactionCode(code: String): List<Violation>

}