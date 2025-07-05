package org.example.taskmasterbackend.repository

import org.example.taskmasterbackend.entity.Subtask
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubtaskRepository : JpaRepository<Subtask, Long>
