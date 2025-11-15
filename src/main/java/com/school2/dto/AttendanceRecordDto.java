package com.school2.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AttendanceRecordDto {
    private Long id;
    private LocalDate date;
    private String status;
    private StudentDto studentInfo;


    public AttendanceRecordDto(Long id, LocalDate date, String status, StudentDto studentInfo) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.studentInfo = studentInfo;
    }

}
