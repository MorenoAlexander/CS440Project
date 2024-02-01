package com.ozil.reborn.repositories

import com.ozil.reborn.entities.Issue
import com.ozil.reborn.entities.Project
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

@org.springframework.stereotype.Repository
interface IssueRepository : JpaRepository<Issue, UUID>{
    fun getIssueById(id: UUID): Optional<Issue>

    fun getAllByProject(project: Project) : List<Issue>
    fun getAllByProjectId(projectId: UUID) : List<Issue>


    fun getIssueByProject(project: Project): List<Issue>
}
