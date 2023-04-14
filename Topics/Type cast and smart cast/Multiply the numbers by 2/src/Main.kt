fun multiplyInts(list: List<Any>): List<Any> {
    val result = mutableListOf<Any>()
    for (item in list) {
        when (item) {
            is Int -> result.add(item * 2)
            !is Int -> result.add(item)
        }
    }
    return result
}