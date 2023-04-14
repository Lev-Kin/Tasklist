import kotlin.math.hypot

fun perimeter(
    x1: Double,
    y1: Double,
    x2: Double,
    y2: Double,
    x3: Double,
    y3: Double,
    x4: Double = x1,
    y4: Double = y1
): Double {

    val length1 = hypot(x1 - x2, y1 - y2)
    val length2 = hypot(x2 - x3, y2 - y3)
    val length3 = hypot(x3 - x4, y3 - y4)
    val length4 = hypot(x4 - x1, y4 - y1)

    return length1 + length2 + length3 + length4
}
