package rmiguele.transaction.validation.repository.impl

import com.mongodb.MongoClient
import dev.morphia.Datastore
import dev.morphia.Morphia
import rmiguele.transaction.validation.repository.BaseRepository

abstract class BaseRepositoryImpl<K, T>(val clazz: Class<T>, mongoClient: MongoClient) : BaseRepository<K, T> {

    val dataStore: Datastore

    init {
        val morphia = Morphia()
        morphia.map(clazz)
        dataStore = morphia.createDatastore(mongoClient, clazz.simpleName)
        dataStore.ensureIndexes()
    }

    override fun save(model: T): T {
        dataStore.save(model)
        return model
    }

    override fun findAll(): List<T> {
        return dataStore.find(clazz).toList()
    }

}