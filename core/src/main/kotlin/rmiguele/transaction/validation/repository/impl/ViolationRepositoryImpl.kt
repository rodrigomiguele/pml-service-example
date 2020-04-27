package rmiguele.transaction.validation.repository.impl

import com.mongodb.MongoClient
import rmiguele.transaction.validation.model.Violation
import rmiguele.transaction.validation.repository.ViolationRepository

class ViolationRepositoryImpl(mongoClient: MongoClient) :
        BaseRepositoryImpl<String, Violation>(Violation::class.java, mongoClient), ViolationRepository {

    override fun getViolationsByTransactionCode(code: String): List<Violation> {
        val query = dataStore.createQuery(Violation::class.java)
        query.field("transactionCode").equal(code)
        return query.find().toList()
    }

    override fun findOne(key: String): Violation? {
        val query = dataStore.createQuery(Violation::class.java)
        query.field("code").equal(key)
        return query.first()
    }
}