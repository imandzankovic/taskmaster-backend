package org.example.taskmasterbackend.service

import org.example.taskmasterbackend.entity.Subtask
import org.example.taskmasterbackend.repository.SubtaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
public class SubtaskService @Autowired constructor(
    private val subtaskRepository: SubtaskRepository
) {
    fun getAllTasks(): List<Subtask> {
        return subtaskRepository.findAll()
    }

    fun getTaskById(id: Long): Optional<Subtask> {
        return subtaskRepository.findById(id)
    }

    fun saveTask(subtask: Subtask): Subtask {
        return subtaskRepository.save(subtask)
    }

    fun deleteTaskById(id: Long) {
        subtaskRepository.deleteById(id)
    }
}