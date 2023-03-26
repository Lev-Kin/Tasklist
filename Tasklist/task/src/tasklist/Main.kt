package tasklist

fun main() {
    val taskList = mutableListOf<String>()
    print("Input the tasks (enter a blank line to end):\n")

    while (true) {
        val taskShort = readln().trim()
        if (taskShort.isBlank()) break
        taskList.add(taskShort)
    }

    if (taskList.isEmpty()) {
        print("No tasks have been input")
    } else {
        for (i in taskList.indices) {
            if (i + 1 < 10) {
                println("${i + 1}  ${taskList[i]}")
            } else {
                println("${i + 1} ${taskList[i]}")
            }
        }
    }
}
