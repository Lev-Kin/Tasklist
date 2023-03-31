package tasklist

class TaskList {
    private val taskList: MutableList<Task> = mutableListOf()

    fun chooseAction() {
        do {
            println("Input an action (add, print, end):")
            val action = readln().lowercase()
            when (action) {
                "add" -> makeTask()
                "print" -> tryPrintTaskList()
                "end" -> end()
                else -> println("The input action is invalid")
            }
        } while (action != "end")
    }

    private fun makeTask() {
        val description = validDescription() ?: return
        val taskToAdd = Task(taskDescription = description)
        taskList.add(taskToAdd)
    }

    private fun validDescription(): List<String>? {
        println("Input a new task (enter a blank line to end):")
        val description = mutableListOf<String>()
        while (true) {
            val taskLine = readln().trim().replace("\t", "")
            if (taskLine.isNotBlank()) description.add(taskLine) else break
        }
        if (description.isEmpty()) {
            println("The task is blank")
            return null
        }
        return description
    }

    private fun tryPrintTaskList(): Boolean {
        if (taskList.isEmpty()) {
            println("No tasks have been input")
            return false
        }

        for (taskIndex in taskList.indices) {
            taskList[taskIndex].printTask(taskIndex)
            println()
        }
        return true
    }

    private fun end() {
        println("Tasklist exiting!")
    }

    data class Task(val taskDescription: List<String>) {

        fun printTask(listIndex: Int) {
            val newFormat = "%-3s%s"
            val extFormat = "%-3s%s"
            val newTaskDesc = fixDescriptionWidth()
            for (taskIndex in newTaskDesc.indices) {
                if (taskIndex == 0) {
                    println(newFormat.format(listIndex + 1, newTaskDesc[taskIndex]))
                } else println(extFormat.format("", newTaskDesc[taskIndex]))
            }
        }

        private fun fixDescriptionWidth(): MutableList<String> {
            val newTaskDescList = mutableListOf<String>()
            for (taskLine in taskDescription.indices) {
                newTaskDescList.addAll(mutableListOf(taskDescription[taskLine]))
            }
            return newTaskDescList
        }

    }
}

fun main() {
    val newList = TaskList()
    newList.chooseAction()
}

