fun solution(strings: List<String>, str: String): Int {
    var count = 0
    for (i in strings) {
        if (i == str) {
            count++
        }
    }

    return count
}