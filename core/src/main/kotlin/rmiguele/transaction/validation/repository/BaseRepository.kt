package rmiguele.transaction.validation.repository

interface BaseRepository<K, T> {

    fun save(model: T)

    fun findOne(key: K): T?

    fun findAll(): List<T>

}