package rmiguele.transaction.validation.application

import com.mongodb.MongoClient
import io.quarkus.arc.DefaultBean
import rmiguele.transaction.validation.repository.PersonRepository
import rmiguele.transaction.validation.repository.TransactionRepository
import rmiguele.transaction.validation.repository.ViolationRepository
import rmiguele.transaction.validation.repository.impl.PersonRepositoryImpl
import rmiguele.transaction.validation.repository.impl.TransactionRepositoryImpl
import rmiguele.transaction.validation.repository.impl.ViolationRepositoryImpl
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces
import javax.inject.Inject

@Dependent
class Repositories {

    @ApplicationScoped
    @Produces
    fun transactionRepository(mongoClient: MongoClient): TransactionRepository {
        return TransactionRepositoryImpl(mongoClient)
    }

    @ApplicationScoped
    @Produces
    fun violationRepository(mongoClient: MongoClient): ViolationRepository {
        return ViolationRepositoryImpl(mongoClient)
    }

    @ApplicationScoped
    @Produces
    fun personRepository(mongoClient: MongoClient): PersonRepository {
        return PersonRepositoryImpl(mongoClient)
    }
}