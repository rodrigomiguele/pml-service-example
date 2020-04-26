package rmiguele.transaction.validation.service.impl

import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import rmiguele.transaction.validation.model.Violation
import rmiguele.transaction.validation.service.AddViolationCommand
import java.util.Calendar
import java.util.Date

class TestUtils {
    companion object {

        inline fun <reified T> capture(captor: ArgumentCaptor<T>): T {
            return when (T::class) {
                AddViolationCommand::class -> captor.capture() ?: AddViolationCommand("", "") as T
                Violation::class -> captor.capture() ?: Violation("", "") as T
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