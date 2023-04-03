package tasklist

import kotlinx.datetime.LocalDate

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
        val priority = validPriority()
        val date = validDate()
        val time = validTime()
        val description = validDescription() ?: return

        val taskToAdd = Task(
            taskDate = date,
            taskTime = time,
            taskPriority = priority,
            taskDescription = description
        )

        taskList.add(taskToAdd)
    }

    private fun validDate(): String {
        return try {
            println("Input the date (yyyy-mm-dd):")
            val input = readln().split("-")
            val date = LocalDate(input[0].toInt(), input[1].toInt(), input[2].toInt())
            date.toString()
        } catch (e: Exception) {
            println("The input date is invalid")
            validDate()
        }
    }

    private fun validTime(): String {
        return try {
            println("Input the time (hh:mm):")
            val (hour, minute) = readln().split(":").map { it.toInt() }
            if (hour in 0..23 && minute in 0..59) {
                "%02d:%02d".format(hour, minute)
            } else throw Exception("The input time is invalid")
        } catch (e: Exception) {
            println("The input time is invalid")
            validTime()
        }
    }

    private fun validPriority(): String {
        var priority: String
        do {
            println("Input the task priority (C, H, N, L):")
            priority = readln().uppercase()
        } while (!priority.matches("[CHNL]".toRegex()))
        return priority
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

    data class Task(
        val taskDate: String,
        private val taskTime: String,
        val taskPriority: String,
        val taskDescription: List<String>
    ) {
        fun printTask(listIndex: Int) {
            val newFormat = "%-3s%s %s %s\n%-3s%3s"
            val extFormat = "%-3s%s"
            val newTaskDesc = fixDescriptionWidth()
            for (taskIndex in newTaskDesc.indices) {
                if (taskIndex == 0) {
                    println(
                        newFormat.format(
                            listIndex + 1,
                            taskDate,
                            taskTime,
                            taskPriority,
                            "",
                            newTaskDesc[taskIndex]
                        )
                    )
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

