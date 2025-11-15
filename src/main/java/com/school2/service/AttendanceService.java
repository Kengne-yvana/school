package com.school2.service;

import com.school2.model.AttendanceRecord;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {
    List<AttendanceRecord> findAttendanceByStudentId(Long studentId);
    AttendanceRecord recordAttendance(AttendanceRecord record);

    List<AttendanceRecord> findAllAttendanceRecords();

    AttendanceRecord updateAttendanceRecord(Long id, AttendanceRecord details);
    void deleteAttendanceRecord(Long id);

    void deleteAttendanceRecordByStudentId(Long studentId);

    AttendanceRecord updateAttendanceRecordByStudentId(Long studentId, AttendanceRecord details);

    Optional<AttendanceRecord> findAttendanceRecordsById(Long id);

    AttendanceRecord recordAttendanceForStudent(AttendanceRecord record);
}
