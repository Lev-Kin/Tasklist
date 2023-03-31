fun main() {
    val size = readln().toInt()
    val array = IntArray(size)

    for (i in 0..array.lastIndex) {
        array[i] = readln().toInt()
    }

    val m = readln().toInt()
    for (i in 0..array.lastIndex) {
        if (array[i] == m) {
            println("YES")
            break
        } else if (i == array.lastIndex) {
            println("NO")
        }
    }
}
