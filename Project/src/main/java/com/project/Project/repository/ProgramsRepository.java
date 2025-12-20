package com.project.Project.repository;

import com.project.Project.model.Programs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramsRepository extends JpaRepository<Programs, Long> {
}
