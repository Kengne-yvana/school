package com.school2.controller;

import com.school2.dto.AttendanceRecordDto;
import com.school2.mapper.AttendanceMapper;
import com.school2.model.AttendanceRecord;
import com.school2.model.Student;
import com.school2.service.AttendanceService;
import com.school2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private final AttendanceService attendanceService;
    private final StudentService studentService; // Inject Student Service
    private final AttendanceMapper attendanceMapper; // Inject the mapper

    public AttendanceController(AttendanceService attendanceService, StudentService studentService, AttendanceMapper attendanceMapper) {
        this.attendanceService = attendanceService;
        this.studentService = studentService;
        this.attendanceMapper = attendanceMapper;
    }

    // Return a List of DTOs
    @GetMapping
    public List<AttendanceRecordDto> getAllAttendanceRecords() {
        List<AttendanceRecord> records = attendanceService.findAllAttendanceRecords();
        return attendanceMapper.toDtoList(records); // Map entities to DTOs
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceRecordDto> getAllAttendanceRecordsById(@PathVariable("id") Long id) {
        return attendanceService.findAttendanceRecordsById(id)
                .map(attendanceMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Return a List of DTOs
    @GetMapping("/students/{studentId}")
    public List<AttendanceRecordDto> getAttendanceByStudent(@PathVariable("studentId") Long studentId) {
        List<AttendanceRecord> records = attendanceService.findAttendanceByStudentId(studentId);
        return attendanceMapper.toDtoList(records); // Map entities to DTOs
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Returns HTTP 201 Created status
    public AttendanceRecordDto recordAttendance(@RequestBody AttendanceRecord record) {
        AttendanceRecord savedRecord = attendanceService.recordAttendance(record);
        return attendanceMapper.toDto(savedRecord); // Map the saved entity to a DTO for the response
    }

    @PostMapping("/students/{studentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AttendanceRecordDto recordAttendanceForStudentUrl(
            @PathVariable Long studentId,
            @RequestBody AttendanceRecord requestBodyRecord) {

        // 1. Fetch the existing student using the ID from the URL
        Student student = studentService.findStudentById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with ID: " + studentId));

        // 2. Set the student object on the attendance record from the request body
        requestBodyRecord.setStudent(student);

        // 3. Save the full record using the service
        AttendanceRecord savedRecord = attendanceService.recordAttendance(requestBodyRecord);

        // 4. Return the DTO response
        return attendanceMapper.toDto(savedRecord);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AttendanceRecordDto> updateAttendance(@PathVariable("id") Long id, @RequestBody AttendanceRecord details) {
        AttendanceRecord updatedRecordEntity = attendanceService.updateAttendanceRecord(id, details);
        // Map the updated entity to a DTO for the response
        AttendanceRecordDto updatedRecordDto = attendanceMapper.toDto(updatedRecordEntity);
        return ResponseEntity.ok(updatedRecordDto); // Returns 200 OK with the DTO body
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<AttendanceRecordDto> updateAttendanceByStudentID(@PathVariable("studentId") Long studentId, @RequestBody AttendanceRecord details) {
        AttendanceRecord updatedRecordEntityByStudentId = attendanceService.updateAttendanceRecordByStudentId(studentId, details);
        // Map the updated entity to a DTO for the response
        AttendanceRecordDto updatedRecordDtoByStudentId = attendanceMapper.toDto(updatedRecordEntityByStudentId);
        return ResponseEntity.ok(updatedRecordDtoByStudentId); // Returns 200 OK with the DTO body
    }

    // DELETE Mapping: Delete an attendance record by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable("id") Long id) {
        attendanceService.deleteAttendanceRecord(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<Void> deleteAttendanceByStudentID(@PathVariable("studentId") Long studentId) {
        attendanceService.deleteAttendanceRecordByStudentId(studentId);
        return ResponseEntity.noContent().build();
    }
}