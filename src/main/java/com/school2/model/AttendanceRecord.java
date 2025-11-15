package com.school2.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "attendance_records")
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    private LocalDate date;
    private String status;

    public AttendanceRecord(Student student, LocalDate date, String status) {
        this.student = student;
        this.date = date;
        this.status = status;
    }
    public AttendanceRecord() {}

}