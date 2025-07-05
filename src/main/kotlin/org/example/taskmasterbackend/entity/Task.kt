package org.example.taskmasterbackend.entity

import jakarta.persistence.*

@Entity
class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String? = null
    var description: String? = null
    var completed: Boolean? = null

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
