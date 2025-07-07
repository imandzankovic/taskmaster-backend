package org.example.taskmasterbackend.service

import org.example.taskmasterbackend.entity.Subtask
import org.example.taskmasterbackend.repository.SubtaskRepository
import org.example.taskmasterbackend.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
public class SubtaskService @Autowired constructor(
    private val subtaskRepository: SubtaskRepository,
    private val taskRepository: TaskRepository
) {
    fun getAllSubtasks(): List<Subtask> {
        return subtaskRepository.findAll()
    }

    fun getSubtaskById(id: Long): Optional<Subtask> {
        return subtaskRepository.findById(id)
    }

    fun saveSubtask(subtask: Subtask): Subtask {
        return subtaskRepository.save(subtask)
    }

    fun createSubtaskWithTask(subtask: Subtask, taskId: Long): Subtask {
        val task = taskRepository.findById(taskId)
            .orElseThrow { RuntimeException("Task not found with id $taskId") }
        subtask.task = task
        return subtaskRepository.save(subtask)
    }
    fun deleteSubtaskById(id: Long) {
        subtaskRepository.deleteById(id)
    }

    fun getSubtasksByTaskId(taskId: Long): List<Subtask> {
        return subtaskRepository.findAllByTaskId(taskId)
    }
}