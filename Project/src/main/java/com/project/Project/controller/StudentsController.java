package com.project.Project.controller;

import com.project.Project.dto.StudentProfileDto;
import com.project.Project.model.Students;
import com.project.Project.model.Users;
import com.project.Project.repository.StudentsRepository;
import com.project.Project.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentsRepository repo;
    private final UsersRepository usersRepo;

    public StudentsController(StudentsRepository repo, UsersRepository usersRepo) {
        this.repo = repo;
        this.usersRepo = usersRepo;
    }

    @GetMapping("/{id}")
    public StudentProfileDto getStudentProfile(@PathVariable Long id) {

        Students s = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return new StudentProfileDto(
                s.getName(),
                s.getRollNo(),
                s.getProgram().getName(),
                s.getSemester(),
                s.getBatch()
        );
    }

    @GetMapping
    public Iterable<Students> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Students student) {

        if (student.getUser() == null || student.getUser().getId() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Missing 'user.id' in request body");
        }

        Optional<Users> userOpt = usersRepo.findById(student.getUser().getId());
        if (userOpt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("User with id " + student.getUser().getId() + " does not exist");
        }

        student.setUser(userOpt.get());

        Students savedStudent = repo.save(student);
        return ResponseEntity.ok(savedStudent);
    }
}
