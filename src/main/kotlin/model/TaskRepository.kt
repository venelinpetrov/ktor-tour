package com.vpe.model

object TaskRepository {

    val tasks = mutableListOf(
        Task("cleaning", "clean the house", Priority.Low),
        Task("gardening", "Mow the lawn", Priority.Medium),
        Task("shopping", "Buy the groceries", Priority.High),
        Task("painting", "Paint the fence", Priority.Medium)
    )

    // all tasks
    fun allTasks() = tasks.toList()

    // tasks by priority
    fun tasksByPriority(priority: Priority) = tasks.filter { it.priority == priority }

    // task by name
    fun taskByName(name: String) = tasks.find {
        it.name.equals(name, ignoreCase = true)
    }

    // add task
    fun addTask(task: Task) {
        if (taskByName(task.name) != null) {
            throw IllegalStateException("Cannot duplicate task names")
        }
        tasks.add(task)
    }
}
