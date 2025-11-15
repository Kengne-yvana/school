package com.school2.service;

import com.school2.model.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> findAllStudents();
    Optional<Student> findStudentById(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    Student updateStudent(Long id, Student studentDetails);
}
