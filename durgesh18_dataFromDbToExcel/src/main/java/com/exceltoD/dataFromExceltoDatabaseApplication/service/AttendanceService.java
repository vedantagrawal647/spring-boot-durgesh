package com.exceltoD.dataFromExceltoDatabaseApplication.service;

import com.exceltoD.dataFromExceltoDatabaseApplication.entity.Attendance;
import com.exceltoD.dataFromExceltoDatabaseApplication.helper.Helper;
import com.exceltoD.dataFromExceltoDatabaseApplication.repo.AttendanceRepo;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;



    public ByteArrayInputStream getActualData() throws IOException {
        List<Attendance> all = attendanceRepo.findAll();
        ByteArrayInputStream byteArrayInputStream = Helper.dataToExcel(all);
        return byteArrayInputStream;
    }
}
