package rmiguele.transaction.validation.service.impl

import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import rmiguele.transaction.validation.model.Transaction
import rmiguele.transaction.validation.model.TransactionType
import rmiguele.transaction.validation.model.Violation
import rmiguele.transaction.validation.service.CreateViolationCommand
import java.util.Calendar
import java.util.Date

class TestUtils {
    companion object {

        inline fun <reified T> capture(captor: ArgumentCaptor<T>): T {
            return when (T::class) {
                CreateViolationCommand::class -> captor.capture() ?: CreateViolationCommand("", "") as T
                Violation::class -> captor.capture() ?: Violation("", "", "") as T
                Transaction::class -> captor.capture() ?: Transaction("", TransactionType.MOBILE, 0.0, Date(), "", "") as T
                else -> captor.capture()
            }
        }

        fun <T> any(): T {
            return Mockito.any<T>()
        }

        fun addMinutes(date: Date, minutes: Int): Date {
            val calendar = Calendar.getInstance()
            calendar.time = date
            calendar.add(Calendar.MINUTE, minutes)
            return calendar.time
        }
    }
}