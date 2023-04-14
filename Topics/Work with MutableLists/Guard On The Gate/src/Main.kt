fun main() {
    val backToTheWall = readLine()!!.split(", ").map { it }.toMutableList()
    val returnedWatchman = readLine()!!
    // do not touch the lines above
    backToTheWall.add(returnedWatchman)
    println(backToTheWall.joinToString())
}