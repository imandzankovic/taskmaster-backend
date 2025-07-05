package org.example.taskmasterbackend.controller

import org.example.taskmasterbackend.entity.Task
import org.example.taskmasterbackend.service.TaskService
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200"], allowedHeaders = ["*"])
@RestController
@RequestMapping("/api/tasks")
class TaskController(
    private val taskService: TaskService
) {

    @GetMapping
    fun getAllTasks(): List<Task> {
        return taskService.getAllTasks()
    }
}
