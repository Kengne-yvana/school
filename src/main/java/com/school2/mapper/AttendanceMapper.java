package com.school2.mapper;

import com.school2.dto.AttendanceRecordDto;
import com.school2.dto.StudentDto;
import com.school2.model.AttendanceRecord;
import com.school2.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component // Make it a Spring component so it can be injected
public class AttendanceMapper {

    // Helper method to convert a Student entity to a StudentDto
    public StudentDto toStudentDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentDto dto = new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
                );
        return dto;
    }

    // Method to convert a single AttendanceRecord entity to a DTO
    public AttendanceRecordDto toDto(AttendanceRecord record) {
        AttendanceRecordDto dto = new AttendanceRecordDto(
             record.getId(),
             record.getDate(),
             record.getStatus(),
             toStudentDto(record.getStudent())
        );
        return dto;
    }

    // Method to convert a List of AttendanceRecord entities to a List of DTOs
    public List<AttendanceRecordDto> toDtoList(List<AttendanceRecord> records) {
        return records.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}