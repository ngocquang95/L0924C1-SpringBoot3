package com.sqc.academy.controller;

import com.sqc.academy.Student;
import com.sqc.academy.dto.ApiResponse;
import com.sqc.academy.exception.AppException;
import com.sqc.academy.exception.ErrorCode;
import com.sqc.academy.util.JsonResponse;
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
    public ResponseEntity<ApiResponse<List<Student>>> getList() {
        return JsonResponse.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable("id") UUID id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .map(JsonResponse::ok)
                .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_EXIST)); // Java 8
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> create(@RequestBody Student student) {
        student.setId(UUID.randomUUID());
        students.add(student);

        return JsonResponse.created(student);
    }
}
