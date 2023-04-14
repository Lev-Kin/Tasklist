fun convertToStringList(list: List<Any>): List<String> {
    val stringList = mutableListOf<String>()
    for (element in list) {
        val stringElement: String? = element as? String
        if (stringElement != null) {
            stringList.add(stringElement)
        } else {
            stringList.add("N/A")
        }
    }
    return stringList
}