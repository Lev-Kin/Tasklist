fun isLeapYear(year: Int): Boolean {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
}

fun main() {
    val year = readln().toInt()
    if (isLeapYear(year)) {
        println("Leap")
    } else {
        println("Regular")
    }
}