import kotlin.math.abs

fun main() {
    val (a, b) = readln().split(" ")
    val x1 = a.toInt()
    val y1 = b.toInt()
    val (c, d) = readln().split(" ")
    val x2 = c.toInt()
    val y2 = d.toInt()

    if (x1 == x2 || y1 == y2) {
        println("YES")
    } else {
        if (abs(x1 - x2) == abs(y1 - y2)) {
            println("YES")
        } else {
            println("NO")
        }
    }
}
