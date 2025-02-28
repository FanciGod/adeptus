package com.adeptus.management.service;

import com.adeptus.management.dto.request.attendance.AttendanceRequest;
import com.adeptus.management.dto.response.AttendanceResponse;

import java.util.List;

public interface IAttendanceService {
    AttendanceResponse markAttendance(AttendanceRequest request);
    List<AttendanceResponse> getAttendanceByClass(Long classId);
}

