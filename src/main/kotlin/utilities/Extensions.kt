package utilities

import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.pow

fun String.convertToByte(): BigDecimal?{
    if (this.isNotEmpty()) {
        return when(this[this.lastIndex].uppercaseChar()) {
            'K' ->  (this.uppercase().replace("K","").toDouble() *Constant.KILO_BYTE).toBigDecimal()
            'M' -> (this.uppercase().replace("M","").toDouble() *Constant.KILO_BYTE.pow(2)).toBigDecimal()
            'G' -> (this.uppercase().replace("G","").toDouble() *Constant.KILO_BYTE.pow(3)).toBigDecimal()
            else -> null
        }
    }
    return null
}

/**
 * Convert the string as an [Date] and returns the result.
 * */
fun String.convertToDate(): Date{
    return  SimpleDateFormat(Constant.DATE_FORMAT).parse(this)
}

