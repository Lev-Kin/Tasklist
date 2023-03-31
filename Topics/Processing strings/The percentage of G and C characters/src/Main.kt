fun main() {
    val s = readln().lowercase()
    var count = 0
    for (i in s.indices) {
        if (s[i] == 'g' || s[i] == 'c') {
            count++
        }
    }
    println((count.toDouble() / s.length) * 100)
}