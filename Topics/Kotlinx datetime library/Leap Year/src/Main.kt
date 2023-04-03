import kotlinx.datetime.*
import java.time.Year

fun isLeapYear(year: String): Boolean {
    // Write your code here
    return Year.isLeap(year.toLong())
    //
}

fun main() {
    val year = readln()
    println(isLeapYear(year))
}