package rmiguele.transaction.validation.repository.impl

import com.mongodb.MongoClient
import dev.morphia.query.Sort
import rmiguele.transaction.validation.model.Transaction
import rmiguele.transaction.validation.model.TransactionType
import rmiguele.transaction.validation.repository.TransactionRepository

class TransactionRepositoryImplImpl(override val mongoClient: MongoClient) :
        BaseRepositoryImpl<String, Transaction>(Transaction::class.java, mongoClient), TransactionRepository {

    override fun getLastTransactionByTypeAndSender(transactionType: TransactionType, senderCode: String): Transaction? {
        val query = dataStore.createQuery(Transaction::class.java)
        query.field("type").equal(transactionType)
        query.field("senderCode").equal(senderCode)
        query.order(Sort.descending("date"))
        return query.first()
    }

    override fun findOne(key: String): Transaction? {
        val query = dataStore.createQuery(Transaction::class.java)
        query.field("code").equal(key)
        return query.first()
    }

}