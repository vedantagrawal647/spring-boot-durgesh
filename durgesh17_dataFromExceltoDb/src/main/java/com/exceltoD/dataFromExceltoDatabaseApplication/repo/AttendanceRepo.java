package com.exceltoD.dataFromExceltoDatabaseApplication.repo;

import com.exceltoD.dataFromExceltoDatabaseApplication.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepo extends JpaRepository<Attendance,Integer> {
}
