package rmiguele.transaction.validation.resource

import io.vertx.core.eventbus.EventBus
import rmiguele.transaction.validation.model.Transaction
import rmiguele.transaction.validation.model.TransactionType
import rmiguele.transaction.validation.service.CreateTransactionCommand
import rmiguele.transaction.validation.service.TransactionService
import rmiguele.transaction.validation.service.ValidateTransactionCommand
import java.util.Date
import javax.inject.Inject
import javax.validation.constraints.NotNull
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

data class CreateTransactionVO(
        @NotNull
        val transactionCode: String,
        @NotNull
        val transactionType: TransactionType,
        @NotNull
        val value: Double,
        @NotNull
        val date: Long,
        @NotNull
        val senderCode: String,
        @NotNull
        val receiverCode: String
)

@Path("/transaction")
class TransactionResource {

    @Inject
    lateinit var transactionService: TransactionService

    @Inject
    lateinit var bus: EventBus

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getTransactions(): List<Transaction> {
        return transactionService.getTransactions()
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createTransaction(command: CreateTransactionVO): Transaction {
        val transaction = transactionService.createTransaction(CreateTransactionCommand(
                command.transactionCode,
                command.transactionType,
                command.value,
                Date(command.date),
                command.senderCode,
                command.receiverCode
        ))
        var validateCommand = ValidateTransactionCommand(
                transaction.code,
                transaction.type,
                transaction.value,
                transaction.date,
                transaction.senderCode,
                transaction.receiverCode
        )
        bus.send("validateTransaction", validateCommand)
        return transaction
    }
}