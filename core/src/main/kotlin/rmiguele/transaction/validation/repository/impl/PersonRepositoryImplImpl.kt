package rmiguele.transaction.validation.repository.impl

import com.mongodb.MongoClient
import rmiguele.transaction.validation.model.Person
import rmiguele.transaction.validation.repository.PersonRepository

class PersonRepositoryImplImpl(override val mongoClient: MongoClient) : BaseRepositoryImpl<String, Person>(Person::class.java, mongoClient), PersonRepository {
    override fun findOne(key: String): Person? {
        val query = dataStore.createQuery(Person::class.java)
        query.field("code").equal(key)
        return query.first()
    }
}