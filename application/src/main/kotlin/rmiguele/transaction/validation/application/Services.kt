package rmiguele.transaction.validation.application

import io.quarkus.arc.DefaultBean
import rmiguele.transaction.validation.repository.PersonRepository
import rmiguele.transaction.validation.repository.TransactionRepository
import rmiguele.transaction.validation.repository.ViolationRepository
import rmiguele.transaction.validation.service.RestrictedListValidationService
import rmiguele.transaction.validation.service.TransactionService
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionService
import rmiguele.transaction.validation.service.ValidateTransactionService
import rmiguele.transaction.validation.service.ViolationService
import rmiguele.transaction.validation.service.impl.RestrictedListValidationServiceImpl
import rmiguele.transaction.validation.service.impl.TransactionServiceImpl
import rmiguele.transaction.validation.service.impl.ValidateCreditCardTransactionServiceImpl
import rmiguele.transaction.validation.service.impl.ValidateTransactionServiceImpl
import rmiguele.transaction.validation.service.impl.ViolationServiceImpl
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces

class Services {

    @ApplicationScoped
    @Produces
    @DefaultBean
    fun transactionService(transactionRepository: TransactionRepository): TransactionService {
        return TransactionServiceImpl(transactionRepository)
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    fun violationService(violationRepository: ViolationRepository): ViolationService {
        return ViolationServiceImpl(violationRepository)
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    fun validateCreditCardTransactionService(transactionRepository: TransactionRepository, violationService: ViolationService): ValidateCreditCardTransactionService {
        return ValidateCreditCardTransactionServiceImpl(transactionRepository, violationService)
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    fun restrictedListValidationService(personRepository: PersonRepository, violationService: ViolationService): RestrictedListValidationService {
        return RestrictedListValidationServiceImpl(personRepository, violationService)
    }

    @ApplicationScoped
    @Produces
    fun validateTransactionService(validateCreditCardTransactionService: ValidateCreditCardTransactionService, restrictedListValidationService: RestrictedListValidationService): ValidateTransactionService {
        return ValidateTransactionServiceImpl(validateCreditCardTransactionService, restrictedListValidationService)
    }
}