fun solution(names: List<String>): Int {
    var index = 0
    for (i in 1 until names.size step 2) {
        if (names[i].first() == 'T') {
            index = i
            break
        }
    }
    return index
}