package org.example.taskmasterbackend.controller

import org.example.taskmasterbackend.entity.Task
import org.example.taskmasterbackend.service.TaskService
import org.springframework.http.ResponseEntity
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
    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Task> {
        val task = taskService.getTaskById(id)
        return task.map { ResponseEntity.ok(it) }
            .orElseGet { ResponseEntity.notFound().build() }
    }

    @PostMapping
    fun createTask(@RequestBody task: Task): ResponseEntity<Task> {
        return ResponseEntity.ok(taskService.saveTask(task))
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody task: Task): ResponseEntity<Task> {
        val optionalTask = taskService.getTaskById(id)
        return if (optionalTask.isPresent) {
            val existing = optionalTask.get()
            existing.name = task.name
            existing.description = task.description
            existing.completed = task.completed

            ResponseEntity.ok(taskService.saveTask(existing))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<Void> {
        taskService.deleteTaskById(id)
        return ResponseEntity.noContent().build()
    }
}
