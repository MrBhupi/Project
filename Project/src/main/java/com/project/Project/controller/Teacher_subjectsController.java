package com.project.Project.controller;

import com.project.Project.dto.TeacherSubjectDTO;
import com.project.Project.model.Teacher_subjects;
import com.project.Project.model.Subjects;
import com.project.Project.model.Teachers;
import com.project.Project.repository.SubjectsRepository;
import com.project.Project.repository.TeacherSubjectsRepository;
import com.project.Project.repository.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher-subjects")
public class Teacher_subjectsController {

    @Autowired
    private TeacherSubjectsRepository teacherSubjectsRepository;

    @Autowired
    private TeachersRepository teachersRepository;

    @Autowired
    private SubjectsRepository subjectsRepository;

    // Create new teacher_subject entry
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TeacherSubjectDTO dto) {
        try {
            Teachers teacher = teachersRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));

            Subjects subject = subjectsRepository.findById(dto.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Subject not found"));

            Teacher_subjects ts = new Teacher_subjects();
            ts.setTeacher(teacher);
            ts.setSubject(subject);
            ts.setStudentBatch(dto.getStudentBatch());
            ts.setStudentProgramId(dto.getStudentProgramId());
            ts.setStudentSemester(dto.getStudentSemester());

            Teacher_subjects saved = teacherSubjectsRepository.save(ts);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all entries
    @GetMapping
    public List<Teacher_subjects> getAll() {
        return teacherSubjectsRepository.findAll();
    }

    // Delete entry by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            teacherSubjectsRepository.deleteById(id);
            return new ResponseEntity<>("Teacher_subject deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
