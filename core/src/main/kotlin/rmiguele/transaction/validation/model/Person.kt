package rmiguele.transaction.validation.model

import dev.morphia.annotations.Entity
import dev.morphia.annotations.Id

@Entity
data class Person(
        @Id
        val code: String,
        val situation: PersonSituation
)

enum class PersonSituation {
    LEGAL, ILLEGAL
}