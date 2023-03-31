fun main() {
    val date = readln()
    val newDate = date.split("-").toMutableList()
    val month = newDate[1]
    val day = newDate[2]
    val year = newDate[0]
    println("$month/$day/$year")
}