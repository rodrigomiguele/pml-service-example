package rmiguele.transaction.validation.model

data class Person(val personCode: String, val personSituation: PersonSituation)

enum class PersonSituation {
    LEGAL, ILLEGAL
}