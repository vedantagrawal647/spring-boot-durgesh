package com.exceltoD.dataFromExceltoDatabaseApplication.service;

import com.exceltoD.dataFromExceltoDatabaseApplication.entity.Attendance;
import com.exceltoD.dataFromExceltoDatabaseApplication.helper.Helper;
import com.exceltoD.dataFromExceltoDatabaseApplication.repo.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;



    public void save(MultipartFile file) {
        try{
            List<Attendance> attendances =  Helper.convertExceltoListOfAttendance(file.getInputStream());
            this.attendanceRepo.saveAll(attendances);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public List<Attendance> getAllAttendance(){
        return this.attendanceRepo.findAll();
    }
}
