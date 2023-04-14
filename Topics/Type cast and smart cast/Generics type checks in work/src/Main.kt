fun <T> countElementsOfType(list: List<Any>, clazz: Class<T>): Int {
    var count = 0
    for (item in list) {
        if (clazz.isInstance(item)) {
            count++
        }
    }
    return count
}