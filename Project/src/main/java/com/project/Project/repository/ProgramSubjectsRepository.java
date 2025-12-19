package com.project.Project.repository;

import com.project.Project.model.Program_subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramSubjectsRepository
        extends JpaRepository<Program_subjects, Long> {
}
