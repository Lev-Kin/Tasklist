package tasklist

import kotlinx.datetime.*

class TaskList {
    private val taskList: MutableList<Task> = mutableListOf()

    fun chooseAction() {
        do {
            println("Input an action (add, print, edit, delete, end):")
            val action = readln().lowercase()
            when (action) {
                "add" -> makeTask()
                "print" -> tryPrintTaskList()
                "edit" -> edit()
                "delete" -> delete()
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

    private fun validPriority(): String {
        var priority: String
        do {
            println("Input the task priority (C, H, N, L):")
            priority = readln().uppercase()
        } while (!priority.matches("[CHNL]".toRegex()))
        return priority
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

    private fun validTaskNumber(): Int {
        return try {
            println("Input the task number (1-${taskList.size}):")
            val taskNum = readln().toInt()
            if (taskNum in 1..taskList.size) taskNum - 1 else throw Exception("Out of Range")
        } catch (e: Exception) {
            println("Invalid task number")
            validTaskNumber()
        }
    }

    private fun validField(): String {
        val validFields = listOf("priority", "date", "time", "task")
        println("Input a field to edit (priority, date, time, task):")
        return try {
            val field = readln()
            if (field in validFields) field else throw Exception("Invalid field")
        } catch (e: Exception) {
            println(e.message)
            validField()
        }
    }

    private fun tryPrintTaskList(): Boolean {
        if (taskList.isEmpty()) {
            println("No tasks have been input")
            return false
        }

        val headerString = "| N  |    Date    | Time  | P | D |                   Task                     |"
        val breakLine = "+----+------------+-------+---+---+--------------------------------------------+"
        println("$breakLine\n$headerString\n$breakLine")
        for (taskIndex in taskList.indices) {
            taskList[taskIndex].printTask(taskIndex)
            println(breakLine)
        }
        return true
    }

    private fun edit() {
        if (!tryPrintTaskList()) return

        val taskNum = validTaskNumber()

        taskList[taskNum] = when (validField()) {
            "priority" -> taskList[taskNum].copy(taskPriority = validPriority())
            "date" -> taskList[taskNum].copy(taskDate = validDate())
            "time" -> taskList[taskNum].copy(taskTime = validTime())
            "task" -> taskList[taskNum].copy(taskDescription = validDescription() ?: return)
            else -> taskList[taskNum]
        }
        println("The task is changed")
    }

    private fun delete() {
        if (!tryPrintTaskList()) return
        taskList.removeAt(validTaskNumber())
        println("The task is deleted")
    }

    private fun end() {
        println("Tasklist exiting!")
    }

    data class Task(
        val taskPriority: String = "N",
        val taskDate: String,
        private val taskTime: String,
        val taskDescription: List<String>
    ) {
        fun printTask(listIndex: Int) {
            val extFormat = "|    |            |       |   |   |%-44s|"
            val newFormat = "| %-3s| %s | %s | %s | %s |%-44s|"
            val newTaskDesc = fixDescriptionWidth()
            for (taskIndex in newTaskDesc.indices) {
                if (taskIndex == 0) {
                    println(
                        newFormat.format(
                            listIndex + 1,
                            taskDate,
                            taskTime,
                            priorityTag(),
                            dueTag(),
                            newTaskDesc[taskIndex]
                        )
                    )
                } else println(extFormat.format(newTaskDesc[taskIndex]))
            }
        }

        private fun fixDescriptionWidth(): MutableList<String> {
            val newTaskDescList = mutableListOf<String>()
            for (taskLine in taskDescription.indices) {
                newTaskDescList.addAll(recursiveDescWidthFix(taskDescription[taskLine]))
            }
            return newTaskDescList
        }

        private fun recursiveDescWidthFix(line: String): MutableList<String> {
            return if (line.length >= 45) {
                val recurse: MutableList<String> = recursiveDescWidthFix(line.substring(44))
                recurse.add(0, line.substring(0, 44))
                recurse
            } else mutableListOf(line)
        }

        private fun dueTag(): String {
            val daysTillDue = Clock.System.now().toLocalDateTime(TimeZone.UTC).date.daysUntil(taskDate.toLocalDate())
            return when {
                (daysTillDue < 0) -> "\u001B[101m \u001B[0m"
                (daysTillDue == 0) -> "\u001B[103m \u001B[0m"
                else -> "\u001B[102m \u001B[0m"
            }
        }

        private fun priorityTag(): String {
            return when (taskPriority) {
                "C" -> "\u001B[101m \u001B[0m"
                "H" -> "\u001B[103m \u001B[0m"
                "N" -> "\u001B[102m \u001B[0m"
                "L" -> "\u001B[104m \u001B[0m"
                else -> " "
            }
        }
    }
}

fun main() {
    val newList = TaskList()
    newList.chooseAction()
}