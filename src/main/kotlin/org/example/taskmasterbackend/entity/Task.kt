package org.example.taskmasterbackend.entity

import jakarta.persistence.*

@Entity
class Task : BaseTask() {

    @OneToMany(mappedBy = "task", cascade = [CascadeType.ALL], orphanRemoval = true)
    var subtasks: MutableList<Subtask> = mutableListOf()

    fun addSubtask(subtask: Subtask) {
        subtasks.add(subtask)
        subtask.task = this
    }

    fun removeSubtask(subtask: Subtask) {
        subtasks.remove(subtask)
        subtask.task = null
    }
}

