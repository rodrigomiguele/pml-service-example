package rmiguele.transaction.validation.resource

import rmiguele.transaction.validation.model.Violation
import rmiguele.transaction.validation.service.GetViolationsByTransactionCodeQuery
import rmiguele.transaction.validation.service.ViolationService
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("/violation")
class ViolationResource {

    @Inject
    lateinit var violationService: ViolationService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getViolations(@QueryParam("transaction") transactionCode: String): List<Violation> {
        return violationService.getViolations(GetViolationsByTransactionCodeQuery(transactionCode))
    }
}