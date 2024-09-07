package com.sqc.academy.controller;

import com.sqc.academy.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {
    private List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(UUID.randomUUID(), "Nguyễn Văn A", 9.6),
                    new Student(UUID.randomUUID(), "Nguyễn Văn B", 9.7),
                    new Student(UUID.randomUUID(), "Nguyễn Văn C", 9.5)
            )
    );

    // API -> list
    // @RequestMapping(value = "/students", method = RequestMethod.GET) // N số nhiều
    @GetMapping
    public ResponseEntity<List<Student>> getList() {
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") UUID id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // Java 8
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        student.setId(UUID.randomUUID());
        students.add(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }
}
