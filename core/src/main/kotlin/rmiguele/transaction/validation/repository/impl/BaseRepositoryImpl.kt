package rmiguele.transaction.validation.repository.impl

import com.mongodb.MongoClient
import dev.morphia.Datastore
import dev.morphia.Morphia
import rmiguele.transaction.validation.repository.BaseRepository

abstract class BaseRepositoryImpl<K, T>(val clazz: Class<T>, open val mongoClient: MongoClient) : BaseRepository<K, T> {

    val dataStore: Datastore

    init {
        val morphia = Morphia().also {
            it.map(clazz)
            dataStore = it.createDatastore(mongoClient, clazz.simpleName)
        }
    }

    override fun save(model: T) {
        dataStore.save(model)
    }

    override fun findAll(): List<T> {
        return dataStore.find(clazz).toList()
    }

}