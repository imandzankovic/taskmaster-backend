package org.example.taskmasterbackend.controller

import org.example.taskmasterbackend.entity.Subtask
import org.example.taskmasterbackend.service.SubtaskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200"], allowedHeaders = ["*"])
@RestController
@RequestMapping("/api/subtasks")
class SubtaskController(
    private val subtaskService: SubtaskService
) {

    @GetMapping
    fun getAllSubtasks(): List<Subtask> {
        return subtaskService.getAllSubtasks()
    }

    @GetMapping("/{id}")
    fun getSubtaskById(@PathVariable id: Long): ResponseEntity<Subtask> {
        val subtask = subtaskService.getSubtaskById(id)
        return subtask.map { ResponseEntity.ok(it) }
            .orElseGet { ResponseEntity.notFound().build() }
    }

    @GetMapping("/task/{taskId}")
    fun getSubtasksByTask(@PathVariable taskId: Long): List<Subtask> {
        return subtaskService.getSubtasksByTaskId(taskId)
    }

    @PostMapping("/task/{taskId}")
    fun createSubtaskForTask(
        @PathVariable taskId: Long,
        @RequestBody subtask: Subtask
    ): ResponseEntity<Subtask> {
        val createdSubtask = subtaskService.createSubtaskWithTask(subtask, taskId)
        return ResponseEntity.ok(createdSubtask)
    }

    @PutMapping("/{id}")
    fun updateSubtask(@PathVariable id: Long, @RequestBody subtask: Subtask): ResponseEntity<Subtask> {
        val optionalSubtask = subtaskService.getSubtaskById(id)
        return if (optionalSubtask.isPresent) {
            val existing = optionalSubtask.get()
            existing.name = subtask.name
            existing.description = subtask.description
            existing.completed = subtask.completed
            existing.task = subtask.task

            ResponseEntity.ok(subtaskService.saveSubtask(existing))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteSubtask(@PathVariable id: Long): ResponseEntity<Void> {
        subtaskService.deleteSubtaskById(id)
        return ResponseEntity.noContent().build()
    }
}
