package rmiguele.transaction.validation.service

import io.quarkus.vertx.ConsumeEvent
import rmiguele.transaction.validation.qualifier.Async
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
@Async
class AsyncValidateTransactionService : ValidateTransactionService {

    @Inject
    lateinit var validateTransactionService: ValidateTransactionService

    @ConsumeEvent("validateTransaction")
    override fun validate(command: ValidateTransactionCommand) {
        validateTransactionService.validate(command)
    }

}