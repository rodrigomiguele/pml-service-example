package rmiguele.transaction.validation.application

import com.mongodb.MongoClient
import io.quarkus.runtime.Startup
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces

class Configuration {

    @Startup
    @ApplicationScoped
    @Produces
    fun mongoClient(): MongoClient {
        return MongoClient()
    }

}