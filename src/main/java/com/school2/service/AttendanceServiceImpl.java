package com.school2.service;

import com.school2.model.AttendanceRecord;
import com.school2.repository.AttendanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService{

    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }
    @Override
    public List<AttendanceRecord> findAttendanceByStudentId(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);    }

    @Override
    public AttendanceRecord recordAttendance(AttendanceRecord record) {
        return attendanceRepository.save(record);    }

    @Override
    public List<AttendanceRecord> findAllAttendanceRecords() {
        return attendanceRepository.findAll();
    }

    @Override
    public AttendanceRecord updateAttendanceRecord(Long id, AttendanceRecord details) {
        AttendanceRecord existingRecord = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found for this id :: " + id));

        existingRecord.setDate(details.getDate());
        existingRecord.setStatus(details.getStatus());

        return attendanceRepository.save(existingRecord);
    }

    @Override
    public void deleteAttendanceRecord(Long id) {
        attendanceRepository.deleteById(id);
    }
    @Override
    public void deleteAttendanceRecordByStudentId(Long studentId) {
        attendanceRepository.deleteById(studentId);
    }

    @Override
    public AttendanceRecord updateAttendanceRecordByStudentId(Long studentId, AttendanceRecord details) {
        AttendanceRecord existingRecord = attendanceRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Attendance record not found for this student id :: " + studentId));

        existingRecord.setDate(details.getDate());
        existingRecord.setStatus(details.getStatus());

        return attendanceRepository.save(existingRecord);    }

    @Override
    public Optional<AttendanceRecord> findAttendanceRecordsById(Long id) {
        return attendanceRepository.findById(id);      }

    @Override
    public AttendanceRecord recordAttendanceForStudent(AttendanceRecord record) {
        return attendanceRepository.save(record);    }

}
