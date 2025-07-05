package org.example.taskmasterbackend.service


import org.example.taskmasterbackend.entity.Task
import org.example.taskmasterbackend.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TaskService @Autowired constructor(
    private val taskRepository: TaskRepository
) {

    fun getAllTasks(): List<Task> {
        return taskRepository.findAll()
    }

    fun getTaskById(id: Long): Optional<Task> {
        return taskRepository.findById(id)
    }

    fun saveTask(task: Task): Task {
        return taskRepository.save(task)
    }

    fun deleteTaskById(id: Long) {
        taskRepository.deleteById(id)
    }
}
