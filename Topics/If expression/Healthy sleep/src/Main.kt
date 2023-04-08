fun main() {
    val a = readln().toInt()
    val b = readln().toInt()
    val h = readln().toInt()

    if (h > b) {
        println("Excess")
    } else if (h < b && h < a) {
        println("Deficiency")
    } else {
        println("Normal")
    }

}