fun <T> getStringsOnly(list: List<T>): List<String> {
    val result = mutableListOf<String>()
    for (element in list) {
        val stringElement: String? = element as? String
        if (stringElement != null) {
            result.add(stringElement)
        }
    }

    return result
}